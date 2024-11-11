package TestDataProviders;

import org.testng.annotations.DataProvider;

import GreenCart.Resources.ExcelUtility;

public class ExcelDataProvider {

	private static final String EXCEL_FILE_PATH = "C:\\Users\\ravi.aggarwal_infobe\\Documents\\Excel Files\\Book2.xlsx";
	private static final String SHEET_NAME = "login";

	@DataProvider(name = "excelData")
	public static Object[][] getExcelData() {
		ExcelUtility excel = new ExcelUtility(EXCEL_FILE_PATH);

		int rowCount = excel.getRowCount(SHEET_NAME);
		int colCount = excel.getColumnCount(SHEET_NAME, 0);

		Object[][] data = new Object[rowCount - 1][colCount];

		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				data[i - 1][j] = excel.getData(SHEET_NAME, i, j);
			}
		}

		excel.close();
		return data;
	}
}
