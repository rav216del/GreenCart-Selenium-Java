package GreenCart.JsonData;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	// Generic method to read data from a JSON file and return it as a Map
	public Map<String, Object> getJsonDataToMap(String filePath) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> dataMap = null;

		try {
			// Read the JSON file and map it to a Map
			dataMap = objectMapper.readValue(new File(filePath), Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	public static void main(String[] args) {
		// Example usage
		DataReader dataReader = new DataReader();

		// Pass the relative path to the JSON file from the project root
		String projectPath = System.getProperty("user.dir"); // Get the project root directory
		String jsonFilePath = projectPath + "\\src\\test\\java\\GreenCart\\JsonData\\PurchaseOrder.json";

		Map<String, Object> jsonData = dataReader.getJsonDataToMap(jsonFilePath);

		// Print the data for demonstration
		if (jsonData != null) {
			jsonData.forEach((key, value) -> System.out.println(key + ": " + value));
		}
	}
}
