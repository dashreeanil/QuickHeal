package com.urlcat.tests.server;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Com.Lib.GenericLib;

public class Test_Automation_01 {
	@BeforeMethod
	public void modifyUrlCategory() throws Exception {
		String sUrl[] = GenericLib.readExcelDataOfColumn(GenericLib.sServerDataFilePath, GenericLib.sServerSheetName,
				GenericLib.sServerCoulmnUrlName, 1);

		for (int i = 0; i < 5; i++) {
			GenericLib.setCellData(GenericLib.sServerDataFilePath, GenericLib.sServerSheetName, 2, "M", i + 1);
			GenericLib.setCellData(GenericLib.sServerDataFilePath, GenericLib.sServerSheetName, 3, "17", i + 1);
		}
	}

	@Test
	public void updateFromServer() throws Exception {
		String rUrl[] = GenericLib.readExcelDataOfColumn(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName,
				GenericLib.sCacheCoulmnUrlName, 0);
		String rDomainFlag[] = GenericLib.readExcelDataOfColumn(GenericLib.sCacheDataFilePath,GenericLib.sCacheSheetName, GenericLib.sCacheCoulmnDomainFlagName, 1);
		String sUrl[] = GenericLib.readExcelDataOfColumn(GenericLib.sServerDataFilePath, GenericLib.sServerSheetName,
				"Url", 1);
		String sDomainFlag[] = GenericLib.readExcelDataOfColumn(GenericLib.sServerDataFilePath,
				GenericLib.sServerSheetName, "DomainFlag", 2);
		String sCategory[] = GenericLib.readExcelDataOfColumn(GenericLib.sServerDataFilePath,
				GenericLib.sServerSheetName, "Category", 3);
		System.out.println(sCategory[3]);
		String flag = "M";
		boolean status = true;
		for (int i = 0; i < sDomainFlag.length; i++) {
			if (sDomainFlag[i].equals(flag)) {
				for (int j = 0; j < rUrl.length; j++) {
					if (sUrl[i].equals(rUrl[j])) {
						GenericLib.setCellData(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName, 1,
								sDomainFlag[i], j + 1);
						GenericLib.setCellData(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName, 2,
								sCategory[i], j + 1);
						status = false;
					}

				}

				if (status == true) {
					GenericLib.setLastCellDataUrl(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName,
							GenericLib.sCacheCoulmnUrlName, sUrl[i], 0);
					GenericLib.setLastCellDataCatagory(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName,
							GenericLib.sCacheCoulmnCategoryName, sCategory[i], 2);
					GenericLib.setLastCellDataDomain(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName,
							GenericLib.sCacheCoulmnDomainFlagName, sDomainFlag[i], 1);
				}
				status = true;
			}
		}

	}

	
}

