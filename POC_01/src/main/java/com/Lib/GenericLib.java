package com.Lib;

import static org.testng.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Listners.CustomListener;

public class GenericLib extends CustomListener{
	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public static String sFile;
	public static String senvTestDataFilePath;
//	static public String sDirPath = System.getProperty("user.dir");
	public static String sInjectorDataFilePath = "C:\\Users\\dashree\\git\\QuickHeal\\POC_01\\src\\main\\resources\\Injector\\Injector.xlsx";
	public static String sCacheDataFilePath ="C:\\Users\\dashree\\git\\QuickHeal\\POC_01\\src\\main\\resources\\ClientCache\\ClientCache.xlsx";
	public static String sServerDataFilePath = "C:\\Users\\dashree\\git\\QuickHeal\\POC_01\\src\\main\\resources\\Server\\Server.xlsx";
	public static String sServerCacheDataFilePath ="C:\\Users\\dashree\\git\\QuickHeal\\POC_01\\src\\main\\resources\\ServerCache\\ServerCache.xlsx";
	public static String sInjectorSheetName ="InjectorClient";
	public static String sInjectorSheetColumnName ="URL";

	/*
	 * @author:Anil & Pawan
	 * 
	 * Description: To read the basic environment settings data from config file
	 * based on Property file value
	 */
	public static String getProprtyValue(String sFile, String sKey) {
		Properties prop = new Properties();
		String sValue = null;
		try {
			InputStream input = new FileInputStream(sFile);
			prop.load(input);
			sValue = prop.getProperty(sKey);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sValue;
	}

	/*
	 * @author:Anil & Pawan
	 * 
	 * 
	 */
	public static Properties getPropertyFile(String sFile) {
		Properties prop = new Properties();
		String sValue = null;
		try {
			InputStream input = new FileInputStream(sFile);
			prop.load(input);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/*
	 * @author:Anil & Pawan 
	 * 
	 * 
	 */
	public static void setPropertyValue(String sFile, String sKey, String sValue) {
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(new File(sFile));
			prop.load(fis);
			fis.close();
			FileOutputStream fos = new FileOutputStream(new File(sFile));
			prop.setProperty(sKey, sValue);
			prop.store(fos, "Updated  file with " + "Key " + sKey + " and Value " + sValue);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * @author: Anil & Pawan 
	 * 
	 * Description:To read test data from excel sheet based on TestcaseID
	 */
	public static String[] readExcelData(String sFilepath, String sSheet, String urlID,int colNum) {
		String sData[] = null;
		try {
			FileInputStream fis = new FileInputStream(sFilepath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			int iRowNum = sht.getLastRowNum();
			for (int i = 0; i <= iRowNum; i++) {
				if (sht.getRow(i).getCell(colNum).toString().equals(urlID)) {
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
		return sData;
	}

	/*
	 * @author: Anil & Pawan 
	 * 
	 * Description: Method to read data based on row
	 * header
	 */

	public static int getColumnIndex(String filepath, String sSheet, String colName) {
		String[] firstRow = GenericLib.readExcelData(filepath, sSheet, "TEST_CASE_NO",0);
		int index = 0;
		for (int i = 0; i < firstRow.length; i++) {
			if (firstRow[i].equalsIgnoreCase(colName)) {
				index = i;
			}
		}
		return index;
	}
	
	public static int getColumnIndex(String filepath, String sSheet, String colName,int colNum) {
		String[] firstRow = GenericLib.readExcelData(filepath, sSheet,colName,colNum);
		int index = -1;
		for (int i = 0; i < firstRow.length; i++) {
			if (firstRow[i].equalsIgnoreCase(colName)) {
				index = i;
			}
		}
		return index;
	}
	
	

	/*
	 * @author: Anil & Pawan 
	 * 
	 * Description: Method to read data based on row
	 * header
	 */

	public static int getProdColumnIndex(String filepath, String sSheet, String colName) {
		String[] firstRow = GenericLib.readExcelData(filepath, sSheet, "CATAGORY",2);
		int index = 0;
		for (int i = 0; i < firstRow.length; i++) {
			if (firstRow[i].equalsIgnoreCase(colName)) {
				index = i;
			}
		}
		return index;
	}

	
	/*
	 * @author: Anil & Pawan Description: Method to read data based on row
	 * header
	 */

	public static int getHeaderColumnIndex(String filepath, String sSheet, String colName) {
		String[] firstRow = GenericLib.readExcelData(filepath, sSheet, "SI No",0);
		int index = 0;
		for (int i = 0; i < firstRow.length; i++) {
			if (firstRow[i].equalsIgnoreCase(colName)) {
				index = i;
			}
		}
		return index;
	}
	
	/*
	 * @author: Anil & Pawan 
	 * Description:Method is used to set data in excel sheet
	 */

	public static void setCellData(String filePath, String sSheet, String sTestCaseID, String columnName, String value)
			throws Exception {
		int columnNumber = getColumnIndex(filePath, sSheet, columnName);
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			// logger.info("----------Sheet " + sSheet);
			int lastRowNum = sht.getLastRowNum();
			for (int i = 0; i <= lastRowNum; i++) {
				if (sht.getRow(i).getCell(0).toString().equals(sTestCaseID)) {
					Row rowNum = sht.getRow(i);
					Cell cell = rowNum.getCell(columnNumber);
					if (cell == null) {
						cell = rowNum.createCell(columnNumber);
						cell.setCellValue(value);
						System.out.println("The Request is succusesfully added"+value);
					} else {
						cell.setCellValue(value);
					}
					break;
				}
			}
			FileOutputStream fileOut = new FileOutputStream(filePath);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}

	/*
	 * @author: 
	 * Description: To split and return the array
	 */
	public static String[] getSplittedArray(String str, String splitChar) {
		return str.split(splitChar);
	}
	/*
	 * @author:
	 * Description: Extract the string based on previous and next strings and occurrences
	 */
	public static String getString(String str, String startStr, int startOccurance, String endStr, int endOccurance) {
		return str.substring(str.indexOf(startStr, startOccurance) + startStr.length(),
				str.indexOf(endStr, endOccurance));
	}

	/*
	 * @author:
	 * Description: Extract the string based on previous and next strings
	 */
	public static String getString(String str, String startStr, String endStr) {
		return str.substring(str.indexOf(startStr) + startStr.length(), str.indexOf(endStr));
	}
	

		
	/* 
	 * description : read a particular column data  
	 * 
	 * 
	 * 
	 */
	public static String[] readExcelDataOfColumn(String sFilepath, String sSheet, String colName) {
		String sData[] = null;
		try {
			FileInputStream fis = new FileInputStream(sFilepath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			int iRowNum = sht.getLastRowNum();
			for (int i = 0; i <= iRowNum-1; i++) {
					int iCellNum = getColumnIndex(sFilepath, sSheet, colName);
					sData = new String[iRowNum];
					sData[i] = sht.getRow(i+1).getCell(iCellNum).getStringCellValue();
				}
			}
		 catch (Exception e) {
			e.printStackTrace();
		}
		return sData;
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public static void refresh() throws Exception{
		 System.out.println(GenericLib.readExcelDataOfColumn(sServerDataFilePath, "Social", "Url"));
		List<String> lst =Arrays.asList(GenericLib.readExcelDataOfColumn(sServerDataFilePath, "Social", "Url"));
		List<String> lst1 =Arrays.asList(GenericLib.readExcelDataOfColumn(sCacheDataFilePath, "Cache", "Url")); 
		int count =0;
		for(int i=0;i<lst1.size();i++){	
			for(int j=0;j<lst.size();j++){
				if(!(lst1.get(i).equals(lst.get(j)))){
					count=count+2;
				}  
				else{
					count=count+1;	
				}
			}
			if(count%2==0){
				GenericLib.setLastCellDataUrl(sCacheDataFilePath, "Cache","Url", lst.get(i),1);
			}
			count=0;
		}
		}
	
	
	public static void updateClientCache(String url) throws Exception
	{
		try {
			FileInputStream fis = new FileInputStream(sCacheDataFilePath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet("Sheet1");
			// logger.info("----------Sheet " + sSheet);
			int lastRowNum = sht.getLastRowNum();
			Row rowNum = sht.getRow(lastRowNum);
			Cell cell = rowNum.getCell(1);
			if (cell == null) {
				cell = rowNum.createCell(1);
				cell.setCellValue(url);
				System.out.println("The Request is succusesfully added"+url);
			} else {
					cell.setCellValue(url);
       				}
			FileOutputStream fileOut = new FileOutputStream(sCacheDataFilePath);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}
	
	public static void validationClientCache(String url,int colNum) throws Exception
	{
		String sData[] = readExcelData(sCacheDataFilePath, "Sheet1",url,colNum);
		int urlInd = getColumnIndex(sCacheDataFilePath, "Sheet1", "Url",colNum);
		String expUrl = sData[urlInd];
		System.out.println(expUrl);
		if(expUrl.equalsIgnoreCase(url))
		{
			System.out.println("Url updated");
		}
		else {
			System.out.println("Url is not updated");
			Assert.fail();
		}
		
	}
	

	/*
	 * 
	 * Description:Method is used to set data in last row  of excel sheet
	 */

	public static void setLastCellDataUrl(String filePath, String sSheet,String columnName, String value,int colNum)
			throws Exception {
		int columnNumber = getColumnIndex(filePath, sSheet, columnName,colNum);
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			// logger.info("----------Sheet " + sSheet);
			int lastRowNum = sht.getLastRowNum()+1;
			System.out.println(lastRowNum);
			Row row = sht.createRow(lastRowNum);
			Row rowNum = sht.getRow(lastRowNum);
			Cell cell = rowNum.getCell(columnNumber);
			if (cell == null) {
				cell = rowNum.createCell(columnNumber);
				cell.setCellValue(value);
				System.out.println("The Request is succusesfully added "+value);
			} else {
					cell.setCellValue(value);
       				}
			FileOutputStream fileOut = new FileOutputStream(filePath);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}
	
		
		public static void setLastCellDataCatagory(String filePath, String sSheet,String columnName, String value,int colNum)
				throws Exception {
			int columnNumber = getColumnIndex(filePath, sSheet, columnName,colNum);
			try {
				FileInputStream fis = new FileInputStream(filePath);
				Workbook wb = (Workbook) WorkbookFactory.create(fis);
				Sheet sht = wb.getSheet(sSheet);
				// logger.info("----------Sheet " + sSheet);
				int lastRowNum = sht.getLastRowNum()-colNum;
				System.out.println(lastRowNum);
				
				Row rowNum = sht.getRow(lastRowNum);
				Cell cell = rowNum.getCell(columnNumber);
				if (cell == null) {
					cell = rowNum.createCell(columnNumber);
					cell.setCellValue(value);
					System.out.println("The Request is succusesfully added "+value);
				} else {
						cell.setCellValue(value);
	       				}
				FileOutputStream fileOut = new FileOutputStream(filePath);
				wb.write(fileOut);
				fileOut.flush();
				fileOut.close();
			} catch (Exception e) {
				throw (e);
			}
}
		public static void setLastCellDataDomain(String filePath, String sSheet,String columnName, String value,int colNum)
				throws Exception {
			int columnNumber = getColumnIndex(filePath, sSheet, columnName,colNum);
			try {
				FileInputStream fis = new FileInputStream(filePath);
				Workbook wb = (Workbook) WorkbookFactory.create(fis);
				Sheet sht = wb.getSheet(sSheet);
				// logger.info("----------Sheet " + sSheet);
				int lastRowNum = sht.getLastRowNum()-colNum;
				System.out.println(lastRowNum);
				
				Row rowNum = sht.getRow(lastRowNum);
				Cell cell = rowNum.getCell(columnNumber);
				if (cell == null) {
					cell = rowNum.createCell(columnNumber);
					cell.setCellValue(value);
					System.out.println("The Request is succusesfully added "+value);
				} else {
						cell.setCellValue(value);
	       				}
				FileOutputStream fileOut = new FileOutputStream(filePath);
				wb.write(fileOut);
				fileOut.flush();
				fileOut.close();
			} catch (Exception e) {
				throw (e);
			}
}
		
		/*extracting content of excle by column index
		 * 
		 * 
		 * */
		
	public 	ArrayList<String> extractExcelContentByColumnIndex(int columnIndex){
	        ArrayList<String> columndata = null;
	        try {
	            File f = new File("sample.xlsx");
	            FileInputStream ios = new FileInputStream(f);
	            XSSFWorkbook workbook = new XSSFWorkbook(ios);
	            XSSFSheet sheet = workbook.getSheetAt(0);
	            Iterator<Row> rowIterator = sheet.iterator();
	            columndata = new ArrayList<String>();

	            while (rowIterator.hasNext()) {
	                Row row = rowIterator.next();
	                Iterator<Cell> cellIterator = row.cellIterator();
	                while (cellIterator.hasNext()) {
	                    Cell cell = cellIterator.next();

	                    if(row.getRowNum() > 0){ //To filter column headings
	                        if(cell.getColumnIndex() == columnIndex){// To match column index
	                            switch (cell.getCellType()) {
	                            case Cell.CELL_TYPE_NUMERIC:
	                                columndata.add(cell.getNumericCellValue()+"");
	                                break;
	                            case Cell.CELL_TYPE_STRING:
	                                columndata.add(cell.getStringCellValue());
	                                break;
	                            }
	                        }
	                    }
	                }
	            }
	            ios.close();
	            System.out.println(columndata);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return columndata;
	    }
	
	public boolean setCellData(String path,String sheetName, int colNumber, int rowNum, String value)
    {
		FileInputStream fis = null;
	    FileOutputStream fos = null;
	    XSSFWorkbook workbook = null;
	    XSSFSheet sheet = null;
	    XSSFRow row = null;
	    XSSFCell cell = null;
	    String xlFilePath;
        try
        {
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rowNum);
            if(row==null)
                row = sheet.createRow(rowNum);
 
            cell = row.getCell(colNumber);
            if(cell == null)
                cell = row.createCell(colNumber);
 
            cell.setCellValue(value);
 
            fos = new FileOutputStream(path);
            workbook.write(fos);
            fos.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return  false;
        }
        return true;
    }
}


