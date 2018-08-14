package com.tests.client;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Lib.GenericLib;
import com.Listners.CustomListener;

public class TestAutomation_02 extends CustomListener{
	@Test(description="updated category received")
	
	public void updateCategoryRecived() throws Exception
	{
	
	String sDataClient[] = GenericLib.readExcelData(GenericLib.sInjectorDataFilePath,GenericLib.sInjectorClientSheetName,"TC_02",0);
	
	int url = GenericLib.getColumnIndex(GenericLib.sInjectorDataFilePath,GenericLib.sInjectorClientSheetName,"URL");
	System.out.println(sDataClient[url]);
	Thread.sleep(2500);
	GenericLib.refresh(sDataClient[url]);
	Thread.sleep(5000);
	GenericLib.validationClientCache(sDataClient[url],0);
		
	
	
}
}
