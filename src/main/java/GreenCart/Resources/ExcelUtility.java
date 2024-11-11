package GreenCart.Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	private Workbook workbook;

	// Constructor to initialize the file path and workbook
	public ExcelUtility(String filePath) {
		try {
			FileInputStream file = new FileInputStream(new File(filePath));
			if (filePath.endsWith(".xlsx")) {
				this.workbook = new XSSFWorkbook(file); // For .xlsx files
			} else if (filePath.endsWith(".xls")) {
				this.workbook = new HSSFWorkbook(file); // For .xls files (Excel 97-2003)
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Method to get data from a specific sheet, row, and column by index
	public String getData(String sheetName, int rowNum, int colNum) {
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(colNum);
		return cell.toString(); // Return value as String
	}

	// Method to get data from a specific sheet, row, and column by column name
	public String getData(String sheetName, String rowName, String colName) {
		int rowNum = getRowIndex(sheetName, rowName);
		int colNum = getColumnIndex(sheetName, colName);
		return getData(sheetName, rowNum, colNum);
	}

	// Method to get the number of rows in a sheet
	public int getRowCount(String sheetName) {
		Sheet sheet = workbook.getSheet(sheetName);
		return sheet.getPhysicalNumberOfRows();
	}

	// Method to get the number of columns in a sheet
	public int getColumnCount(String sheetName, int rowNum) {
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		return row.getPhysicalNumberOfCells();
	}

	// Method to find the row index based on a unique value in the first column
	public int getRowIndex(String sheetName, String rowName) {
		Sheet sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getPhysicalNumberOfRows();
		for (int i = 0; i < rowCount; i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				Cell cell = row.getCell(0); // Assuming the first column contains unique identifiers (row names)
				if (cell != null && cell.toString().equals(rowName)) {
					return i; // Return the row index
				}
			}
		}
		return -1; // Return -1 if the row is not found
	}

	// Method to find the column index based on the column header name
	public int getColumnIndex(String sheetName, String colName) {
		Sheet sheet = workbook.getSheet(sheetName);
		Row headerRow = sheet.getRow(0); // Assuming the first row contains column headers
		int colCount = headerRow.getPhysicalNumberOfCells();
		for (int i = 0; i < colCount; i++) {
			Cell cell = headerRow.getCell(i);
			if (cell != null && cell.toString().equals(colName)) {
				return i; // Return the column index
			}
		}
		return -1; // Return -1 if the column is not found
	}

	// Method to close the workbook
	public void close() {
		try {
			if (workbook != null) {
				workbook.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
