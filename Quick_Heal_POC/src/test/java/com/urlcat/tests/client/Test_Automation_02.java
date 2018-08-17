package com.urlcat.tests.client;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Com.Lib.GenericLib;

public class Test_Automation_02 {

	@BeforeMethod
	public void modifyUrlCategory() throws Exception {
		String rUrl[] = GenericLib.readExcelDataOfColumn(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName,GenericLib.sCacheCoulmnUrlName, 0);
		String sUrl[] = GenericLib.readExcelDataOfColumn(GenericLib.sServerDataFilePath,GenericLib.sServerSheetName,GenericLib.sCacheCoulmnUrlName, 1);
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < sUrl.length; j++) {
				if (rUrl[i].equals(sUrl[j])) {
					GenericLib.setCellData(GenericLib.sServerDataFilePath,GenericLib.sServerSheetName, 2, "M", j + 1);
					GenericLib.setCellData(GenericLib.sServerDataFilePath,GenericLib.sServerSheetName, 3, "16", j + 1);
				}
			}
		}
	}

	@Test
	public void updatedCategoryReceived() throws Exception {
		String rUrl[] = GenericLib.readExcelDataOfColumn(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName,GenericLib.sCacheCoulmnUrlName, 0);
		for (int i = 0; i < rUrl.length; i++) {
			GenericLib.cacheRefreshRequest();
		}
	}



}
