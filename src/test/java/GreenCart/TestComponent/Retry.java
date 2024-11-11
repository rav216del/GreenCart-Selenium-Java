package GreenCart.TestComponent;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	private int count = 0;
	private int maxTry = 2;

	@Override
	public boolean retry(ITestResult result) {
		if (count < maxTry) {
			count++;
			return true; // Retry the test
		}
		return false; // Do not retry if the max count is reached
	}
}
