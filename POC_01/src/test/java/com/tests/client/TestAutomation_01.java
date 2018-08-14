package com.tests.client;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Init.dataFilePath;
import com.Lib.GenericLib;
import com.Listners.CustomListener;

public class TestAutomation_01 extends CustomListener {
	
	@Test(description="To check if Domain/URL is present")
	public void checkForDomain() throws Exception
	{
		String sDataClient[] = GenericLib.readExcelData(GenericLib.sInjectorDataFilePath,GenericLib.sInjectorClientSheetName,"TC_01",0);
		
		int url = GenericLib.getColumnIndex(GenericLib.sInjectorDataFilePath,GenericLib.sInjectorClientSheetName,"URL");
		System.out.println(sDataClient[url]);
		GenericLib.setLastCellDataUrl(GenericLib.sCacheDataFilePath,"Sheet1","Url",sDataClient[url],0);
		GenericLib.setLastCellDataCatagory(GenericLib.sCacheDataFilePath,"Sheet1","Category","12",1);
		GenericLib.setLastCellDataDomain(GenericLib.sCacheDataFilePath,"Sheet1","DomainFlag","A",2);

		Thread.sleep(5000);
		GenericLib.validationClientCache(sDataClient[url],0);
	}

}
