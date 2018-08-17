package com.urlcat.tests.client;

import org.testng.annotations.Test;

import Com.Lib.GenericLib;

public class Test_Automation_01 {
	
	
	@Test(description="Test to validate the domain present in cache")
	public void checkForDomain() throws Exception
	{
		String sdata[]=GenericLib.readExcelDataOfColumn(GenericLib.sInputDataFilePath, GenericLib.sInputSheetName, GenericLib.sInputCoulmnUrlName,0);
		for(int i=0;i<=sdata.length-1;i++){
			String[] urlInfo=GenericLib.checkClientCache(GenericLib.sCacheDataFilePath,sdata[i]);
			if(urlInfo!=null){
			GenericLib.setLastCellDataUrl(GenericLib.sCacheDataFilePath,GenericLib.sCacheSheetName , GenericLib.sCacheCoulmnUrlName, urlInfo[1], 0);
			GenericLib.setLastCellDataCatagory(GenericLib.sCacheDataFilePath,GenericLib.sCacheSheetName, GenericLib.sCacheCoulmnCategoryName,urlInfo[3],2);
			GenericLib.setLastCellDataDomain(GenericLib.sCacheDataFilePath,GenericLib.sCacheSheetName,GenericLib.sCacheCoulmnDomainFlagName,urlInfo[2],1);
			}
		}
	
	}
}
