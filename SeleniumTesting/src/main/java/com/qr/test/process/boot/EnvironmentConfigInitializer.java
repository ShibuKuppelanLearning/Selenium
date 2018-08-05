package com.qr.test.process.boot;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qr.test.env.config.CoreEnvConfig;
import com.qr.test.utility.JSONReaderUtility;

public class EnvironmentConfigInitializer {

	private EnvironmentConfigInitializer() {
		// TODO Auto-generated constructor stub
	}

	public WebDriver getWebDriver() throws JsonParseException, JsonMappingException, IOException {
		WebDriver webDriver = null;
		CoreEnvConfig coreEnvConfig = null;
		coreEnvConfig = loadAndGetConfiguration();
		switch (coreEnvConfig.getBrowserType()) {
		case GOOGLE_CHROME:
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			webDriver = new ChromeDriver(options);
			break;
		case INTERNET_EXPLORER:
			webDriver = new InternetExplorerDriver();
			break;
		case OPERA:
			webDriver = new OperaDriver();
			break;
		case SAFARI:
			webDriver = new SafariDriver();
			break;
		case FIREFOX:
			webDriver = new FirefoxDriver();
			break;
		default:
			break;
		}
		return webDriver;
	}

	@SuppressWarnings("unchecked")
	private void configureSystemProperties(String browserSpecificConfPath)
			throws IOException, JsonParseException, JsonMappingException {
		Map<String, String> systemProperties = null;
		systemProperties = JSONReaderUtility.getJsonReaderUtility().readJson(browserSpecificConfPath, Map.class);
		for (Entry<String, String> entry : systemProperties.entrySet()) {
			System.setProperty(entry.getKey(), entry.getValue());
		}
	}

	private CoreEnvConfig loadAndGetConfiguration() throws JsonParseException, JsonMappingException, IOException {
		CoreEnvConfig coreEnvConfig = null;
		coreEnvConfig = JSONReaderUtility.getJsonReaderUtility().readJson("/com/test/env/config/config.json",
				CoreEnvConfig.class);
		configureSystemProperties("/com/test/env/config/systemProperties.json");
		return coreEnvConfig;
	}

	public static EnvironmentConfigInitializer newInstance() {
		return new EnvironmentConfigInitializer();
	}
}
