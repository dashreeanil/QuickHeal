package com.Lib;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class Demo {
	
	public static void validateCacheData(String sFilepath, String sSheet, String url) {
		String sData[] = null;
		try {
			FileInputStream fis = new FileInputStream(sFilepath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			int iRowNum = sht.getLastRowNum();
			for (int i = 0; i <= iRowNum; i++) {
				if (sht.getRow(i).getCell(0).toString().equals(url)) {
					int iCellNum = sht.getRow(i).getPhysicalNumberOfCells();
					sData = new String[iCellNum];
					for (int j = 0; j < iCellNum; j++) {
						sData[j] = sht.getRow(i).getCell(j).getStringCellValue();
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public static void refresh(String filePath, String sSheet,String value,int refreshTime)
			throws Exception {
		refreshTime=refreshTime+2;
		//int columnNumber = getColumnIndex(filePath, sSheet, columnName);
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			// logger.info("----------Sheet " + sSheet);
			int lastRowNum = sht.getLastRowNum();
			for (int i = lastRowNum; i <= refreshTime; i++) {
					Row rowNum = sht.getRow(i);
					int cellNum=rowNum.getLastCellNum();
					Cell cell = rowNum.getCell(cellNum);
					cell.setCellValue(value);
				}
		
			FileOutputStream fileOut = new FileOutputStream(filePath);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
		}
		 catch (Exception e) {
			throw (e);
		}
	}
	
	@Test
	public void demo()
	{
		refresh("./Users/dashree/git/QuickHeal/POC_01/src/main/resources/ClientCache/ClientCache.xlsx", sSheet, value, refreshTime);
	}
}



