package com.tests.client;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Init.dataFilePath;
import com.Lib.GenericLib;
import com.Listners.CustomListener;

public class TestAutomation_01 extends CustomListener {
	
	@Test(description="To check if Domain/URL is present")
	public void checkForDomain()
	{
		String sDataClient[] = GenericLib.readExcelData(GenericLib.sInjectorDataFilePath,GenericLib.sInjectorSheetName,"TC_01");
		int url = GenericLib.getColumnIndex(GenericLib.sInjectorDataFilePath,GenericLib.sInjectorSheetName,"URL");
		System.out.println(sDataClient[url]);
		
		if(true)
		{
			
		}
	}

}
