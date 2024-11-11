package TestFolder;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import GreenCart.Resources.ExcelUtility;

public class ExcelExampleTest {

	@Test(dataProvider = "testData")
	public void testUsingExcel(String username, String password) {
		System.out.println("Username: " + username);
		System.out.println("Password: " + password);
	}

	@DataProvider(name = "testData")
	public Object[][] getTestData() {
		ExcelUtility excel = new ExcelUtility("C:\\Users\\ravi.aggarwal_infobe\\Documents\\Excel Files\\Book1.xlsx");

		int rowCount = excel.getRowCount("Sheet1"); // Specify your sheet name
		int colCount = excel.getColumnCount("Sheet1", 0); // Get number of columns

		Object[][] data = new Object[rowCount - 1][colCount]; // Assuming first row is header

		// Loop through the data in Excel and store it in the Object[][] array
		for (int i = 1; i < rowCount; i++) { // Start from row 1 to skip header
			for (int j = 0; j < colCount; j++) {
				data[i - 1][j] = excel.getData("Sheet1", i, j); // Adjust according to your sheet
			}
		}

		excel.close();
		return data;
	}
}
