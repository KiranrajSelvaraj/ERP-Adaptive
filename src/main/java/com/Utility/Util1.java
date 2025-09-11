package com.Utility;


import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class Util1 {
	
	static XSSFWorkbook book;
	static XSSFSheet sheet;

	public static Object[][] getTestData(String FilePath, String sheetname) {
		
		FileInputStream file = null;

		try {
			file = new FileInputStream(FilePath);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			book = (XSSFWorkbook) WorkbookFactory.create(file);

		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetname);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getPhysicalNumberOfCells()];
		/*
		 * System.out.println(sheet.getLastRowNum()+"-----------"+
		 * sheet.getRow(0).getLastCellNum());
		 */
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i + 1);

			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				if (row == null)
					data[i][j] = "";
				else {
					Cell cell = row.getCell(j);
					if (cell == null)
						data[i][j] = "";
					else {

						DataFormatter df = new DataFormatter();
						String value = df.formatCellValue(cell);

						data[i][j] = value;
					}
				}

			}

		}

		return data;

	}

	public static void main(String[] args) {

	}


}
