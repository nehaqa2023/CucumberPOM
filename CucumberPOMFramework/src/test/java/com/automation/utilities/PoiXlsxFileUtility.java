package com.automation.utilities;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiXlsxFileUtility {
	
	
	
	public static void readCellDataFromXLfile(File path,String sheetName,int rowNum,int cellNum) throws IOException  {
		XSSFWorkbook workbook= null;
	     
		try {
			workbook = new XSSFWorkbook(path);
			System.out.println(workbook.getSheetName(0));
			XSSFSheet sheet= workbook.getSheet(sheetName);
			XSSFRow row= sheet.getRow(rowNum);
			XSSFCell cell= row.getCell(cellNum);
			if(cell.getCellType()== CellType.NUMERIC)
				System.out.println("data is="+cell.getNumericCellValue());
			else if(cell.getCellType()== CellType.STRING)
				System.out.println("data is="+cell.getStringCellValue());
			
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public static void readAllCellDataFromXLfile(File path,String sheetName) {
		XSSFWorkbook workbook=null;
		try {
			workbook = new XSSFWorkbook(path);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		XSSFSheet sheet= workbook.getSheet(sheetName);
		Iterator<Row> rows= sheet.iterator();
		while(rows.hasNext()) {
			Row row = rows.next();
			Iterator<Cell> cells = row.iterator();
			while(cells.hasNext()) {
				Cell cell = cells.next();
				if(cell.getCellType()== CellType.NUMERIC)
					System.out.print(cell.getNumericCellValue()+" ");
				else if(cell.getCellType()== CellType.STRING)
					System.out.print(cell.getStringCellValue()+" ");
			}
			System.out.println();
		}
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void readAllCellDataFromAllXLSheets(File file) {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void writeXLSXCellData(File path,String sheetName,int row,int column,String data) throws IOException  {
		
		FileInputStream fs = null;
		XSSFWorkbook workbook = null;
		FileOutputStream fo= null;
		try {
			fs = new FileInputStream(path);
			workbook = new XSSFWorkbook(fs);
			
			XSSFSheet sheet= workbook.getSheet(sheetName);
			
			XSSFRow rowData= sheet.getRow(row);
			XSSFCell cell= rowData.getCell(column);
			cell.setCellValue(data);
		  fo = new FileOutputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
		workbook.write(fo);
		fs.close();
		fo.flush();
		fo.close();
		workbook.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("complete writing");
		
		
	}
	public static void writeXLSXCellData(File path,String sheetName,int row,int column,int data) throws IOException  {
		
		FileInputStream fs = null;
		XSSFWorkbook workbook = null;
		FileOutputStream fo= null;
		try {
			fs = new FileInputStream(path);
			workbook = new XSSFWorkbook(fs);
			
			XSSFSheet sheet= workbook.getSheet(sheetName);
			int sizeOfRow = sheet.getLastRowNum()+1; //to get size of row
			XSSFRow rowData= sheet.getRow(row);
			int sizeOfCell = rowData.getLastCellNum();
			XSSFCell cell= rowData.getCell(column);
			cell.setCellValue(data);
		  fo = new FileOutputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
		workbook.write(fo);
		fs.close();
		fo.flush();
		fo.close();
		workbook.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("complete writing");
	}
	public static void createsheetAndAddData(File path,String sheetName) throws IOException  {
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(path);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet newSheet = workbook.createSheet(sheetName);
		XSSFRow newRow = newSheet.createRow(0);
		XSSFCell cell1 = newRow.createCell(0,CellType.STRING);
		XSSFCell cell2 = newRow.createCell(1,CellType.NUMERIC);
		//CellStyle style=cell1.getCellStyle();
		//cell1.setCellStyle(null);
		cell1.setCellValue("java");
	    cell2.setCellValue(9);
	    FileOutputStream fo = null;
		try {
			fo = new FileOutputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    workbook.write(fo);
	    fo.flush();
	    fo.close();
		workbook.close();
	}

}