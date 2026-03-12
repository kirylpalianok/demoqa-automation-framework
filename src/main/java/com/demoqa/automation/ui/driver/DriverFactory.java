package com.demoqa.automation.ui.driver;

import com.demoqa.automation.config.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

	private DriverFactory() {}

	public static WebDriver createDriver() {

		String browserName = ConfigManager.getConfig().browser().toUpperCase();
		BrowserType browserType = BrowserType.valueOf(browserName);

		switch (browserType) {
			case CHROME:
				return createChromeDriver();
			case FIREFOX:
				return createFireFoxDriver();
			default:
				throw new IllegalArgumentException("Unsupported browser: " + browserName);
		}
	}

	private static WebDriver createChromeDriver() {
		ChromeOptions options = new ChromeOptions();

		if (ConfigManager.getConfig().headless()) {
			options.addArguments("--headless=new");
		}

		options.addArguments("--window-size=1920,1080");

		return new ChromeDriver(options);
	}

	private static WebDriver createFireFoxDriver() {
		FirefoxOptions options = new FirefoxOptions();

		if (ConfigManager.getConfig().headless()) {
			options.addArguments("--headless=new");
		}

		options.addArguments("--window-size=1920,1080");

		return new FirefoxDriver(options);
	}
}
