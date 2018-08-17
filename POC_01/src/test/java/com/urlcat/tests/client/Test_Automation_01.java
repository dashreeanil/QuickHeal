package com.urlcat.tests.client;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import Com.Lib.GenericLib;

public class Test_Automation_01 {
	Logger logger;
	
	@Test
	public void checkForDomain() throws Exception
	{
		logger = Logger.getLogger("Check for Domain Starts");
		String sdata[]=GenericLib.readExcelDataOfColumn(GenericLib.sInputDataFilePath, GenericLib.sInputSheetName, GenericLib.sInputCoulmnUrlName,0);
		for(int i=0;i<=sdata.length-1;i++){
			String[] urlInfo=GenericLib.checkClientCache(GenericLib.sCacheDataFilePath,sdata[i]);
			logger.debug("Fetching the domain from resourses");
			if(urlInfo!=null){
			GenericLib.setLastCellDataUrl(GenericLib.sCacheDataFilePath,GenericLib.sCacheSheetName , GenericLib.sCacheCoulmnUrlName, urlInfo[1], 0);
			logger.debug("Domain has been succesfully added the cache");
			GenericLib.setLastCellDataCatagory(GenericLib.sCacheDataFilePath,GenericLib.sCacheSheetName, GenericLib.sCacheCoulmnCategoryName,urlInfo[3],2);
			logger.debug("Catagory has been succesfully added the cache");
			GenericLib.setLastCellDataDomain(GenericLib.sCacheDataFilePath,GenericLib.sCacheSheetName,GenericLib.sCacheCoulmnDomainFlagName,urlInfo[2],1);
			logger.debug("Domain Flag has been succesfully added the cache");
			}
		}
	
	}
}
