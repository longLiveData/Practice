package com.dataprocess.twitter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * load data from file.xlsx by poi
 */
public class DataLoad {

	private String filePath;
	private static int firstColumn = 0;
	private static int lastColumn = 10;

	/**
	 * Constructor
	 * @param filePath path of xlsx file
	 */
	public DataLoad(String filePath){
		this.filePath = filePath;
	}

	/**
	 * read data from xlsx file
	 * @return a ArrayList contains data
	 * @throws IOException
	 */
	public ArrayList<ArrayList<String>> getAllData() throws IOException {
		ArrayList<ArrayList<String>> tableInfo = new ArrayList<>();

		// open file
		File excelFile = new File(filePath);
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(excelFile));
		XSSFSheet sheet1 = wb.getSheetAt(0);
		DataFormatter formatter = new DataFormatter();

		// get rows of file
		for (Row row : sheet1) {

			// put each line info into lineInfo[]
			ArrayList<String> lineInfo = new ArrayList<>();

			// read 11 cells, process null cell
			for (int cn = firstColumn; cn <= lastColumn; cn++) {
				Cell cell = row.getCell(cn);
				if (cell == null) {
					lineInfo.add("0");
				} else {
					String text = formatter.formatCellValue(cell);
					lineInfo.add(text);
				}
			}

			// put each lineInfo[] into tableInfo[]
			tableInfo.add(lineInfo);
		}

		return tableInfo;
	}
        
        public ArrayList<ArrayList<String>> getTwitterData() throws IOException {
		ArrayList<ArrayList<String>> tableInfo = new ArrayList<>();

		// open file
		File excelFile = new File(filePath);
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(excelFile));
		XSSFSheet sheet1 = wb.getSheetAt(0);
		DataFormatter formatter = new DataFormatter();

		// get rows of file
		for (Row row : sheet1) {

			// put each line info into lineInfo[]
			ArrayList<String> lineInfo = new ArrayList<>();

			// read only 0 and 5
                        Cell cell0 = row.getCell(0);
                        String text0 = formatter.formatCellValue(cell0);
                        lineInfo.add(text0);

                        Cell cell5 = row.getCell(5);
                        String text5 = formatter.formatCellValue(cell5);
                        lineInfo.add(text5);
				
			// put each lineInfo[] into tableInfo[]
			tableInfo.add(lineInfo);
		}

		return tableInfo;
	}
}
