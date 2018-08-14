package com.Lib;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * @author
 *
 */
public class ExcelUtil {
	
/*	public static String sheetName = InitObjects.currentTestCaseName;
	public static String xlPath = InitObjects.testDataSheetPath;*/
	
	
	 public static String getAllValueOf(String xlPath,String sheetName,String content)
     {
		 
		// System.out.println(sheetName);
		 //System.out.println(sheetName +" AND "+xlPath + " AND "+content);
		 
		 ArrayList<String> valueOfLabelInExcel = new ArrayList<String>();
		 ArrayList<Integer> cellIndexOfLabelInExcel = new ArrayList<Integer>();
		 try {
			cellIndexOfLabelInExcel = findCellIndexOfLabelInExcel(xlPath,sheetName,content);
			
                             FileInputStream fis = new FileInputStream(xlPath);
                             Workbook w1 = WorkbookFactory.create(fis);
                             Sheet s1 = w1.getSheet(sheetName);  
                             if(cellIndexOfLabelInExcel.size()==0)
                             {
                            	 System.out.println("Label Not Found In Excel");
                            	 //Assert.fail("Label Not Found In Excel");
                             }
                             else
                             {
	                             for(int i=cellIndexOfLabelInExcel.get(0)+1;i<=s1.getLastRowNum();i++)
	                             {                            	 
	                            	 Row r1 = s1.getRow(i);
	                            	 try
	                            	 {
		                            	 Cell c1 = r1.getCell(cellIndexOfLabelInExcel.get(1));
		                            	 if(c1!=null)
		                            	 {
		                            		 valueOfLabelInExcel.add(c1.getStringCellValue());                            		
		                            	 }
	                            	 }
	                            	 catch(NullPointerException e)
	                            	 {
	                            		break; 
	                            	 }
	                             }
                             }
                      
	                
		 	}
		
                 catch (Exception e)
                 {
                             e.printStackTrace();                            
                 }
		 
		 
		 		return valueOfLabelInExcel.get(0);          
     }

	 
	
	     public static void setData (String xlPath,String sheetName,int rowNum, int cellNum,String data)
	     {
	      
	                 try
	                 {
	                             FileInputStream fis = new FileInputStream(xlPath);
	                             Workbook w1 = WorkbookFactory.create(fis);
	                             Sheet s1 = w1.getSheet(sheetName);
	                             Row r1 = s1.getRow(rowNum);
	                             Cell c1 = r1.createCell(cellNum);
	                             c1.setCellValue(data);
	                             FileOutputStream fos = new FileOutputStream(xlPath);
	                             w1.write(fos);
	                 }
	                 catch (Exception e)
	                 {
	                             e.printStackTrace();                 
	}
	     }




	     public static ArrayList<Integer> findCellIndexOfLabelInExcel(String xlPath, String sheetName, String content) throws EncryptedDocumentException, IOException, InvalidFormatException
		 {
			 ArrayList<Integer> labelIndexInExcel = new ArrayList<Integer>();
			 
			 FileInputStream fis = new FileInputStream(xlPath);
	         Workbook wb = WorkbookFactory.create(fis);
	         Sheet sh = wb.getSheet(sheetName);
	         
	         for(int i=0;i<sh.getLastRowNum();i++)
	         {
		         Row row = sh.getRow(i);
		         try
				 {
			         int lastCellCount = row.getLastCellNum();		         		
					 for(int j=0 ; j<lastCellCount;j++)
					 {
					 
						 try
						 {
							 Cell cellValue = row.getCell(j);			 
							 if(cellValue.toString().equals(content))
							 {				 
								 labelIndexInExcel.add(i);
								 labelIndexInExcel.add(j);
								 break;
							 }
						 }
						 catch(Exception e)
						 {
							 
						 }
					 }
					 
				 }
		         catch(NullPointerException e)
				 {
					 
				 }
	         }
	         
			/* if(labelFoundInExcel==false)
			 {	
				 
				// Assert.fail("Given Label Is Not Found In the Excel Sheet");
				 System.out.println(("Given Label Is Not Found In the Excel Sheet"));
			 }
	 */
				return labelIndexInExcel;
	 
		 }


}
