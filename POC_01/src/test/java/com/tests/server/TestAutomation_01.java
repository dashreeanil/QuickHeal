package com.tests.server;

import java.util.Random;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Lib.GenericLib;
import com.Listners.CustomListener;

public class TestAutomation_01 extends CustomListener{
	@BeforeMethod
	public void updateServerCategory() throws Exception
	{
		Random rand = new Random();
		int  n = rand.nextInt(35) + 1;
		int urlPosition = 2;
		GenericLib.setServerCellData(GenericLib.sServerDataFilePath, "Social", "DomainFlag", "M", n, urlPosition);
		GenericLib.setServerCellData(GenericLib.sServerDataFilePath, "Social", "Category", "13", n, urlPosition);
		
	}
	
	@Test(description="updated category received")
	
	public void updateCategoryRecived() throws Exception
	{
	
	String sDataClient[] = GenericLib.readExcelData(GenericLib.sInjectorDataFilePath,GenericLib.sInjectorServerSheetName,"TC_01",0);
	
	int url = GenericLib.getColumnIndex(GenericLib.sInjectorDataFilePath,GenericLib.sInjectorServerSheetName,"URL");
	System.out.println(sDataClient[url]);
	Thread.sleep(2500);
	GenericLib.refresh(sDataClient[url]);
	Thread.sleep(5000);
	GenericLib.validationClientCache(sDataClient[url],0);
		
	
	
}
}
