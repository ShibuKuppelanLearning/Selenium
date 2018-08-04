package com.qr.test;

import org.openqa.selenium.WebDriver;

import com.qr.test.process.boot.EnvironmentConfigInitializer;
import com.qr.test.process.input.TestRunnerInput;
import com.qr.test.process.test.TestRunner;
import com.qr.test.utility.JSONReaderUtility;

public class Main {
	public static void main(String[] args) {
		EnvironmentConfigInitializer initializer = null;
		WebDriver webDriver = null;
		try {
			/** Initialize the web browser environment */
			initializer = EnvironmentConfigInitializer.newInstance();
			webDriver = initializer.getWebDriver();
			if (webDriver == null) {
				throw new RuntimeException("Invalid configuration");
			}

			/** Load the input data */
			TestRunnerInput testRunnerInput = JSONReaderUtility.getJsonReaderUtility()
					.readJson("/com/test/app/urlAndData.json", TestRunnerInput.class);

			/** Initialize the web browser environment */
			TestRunner testRunner = TestRunner.newInstance();
			testRunner.startTest(webDriver,testRunnerInput);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
