package com.demoqa.automation.ui.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

	private DriverManager() {}

	public static void initDriver() {
		if (DRIVER.get() == null) {
			WebDriver driver = DriverFactory.createDriver();
			DRIVER.set(driver);
		}
	}

	public static WebDriver getDriver() {
		WebDriver driver = DRIVER.get();

		if (driver == null) {
			throw new IllegalStateException("Driver is not initialized. Call initDriver() first.");
		}

		return driver;
	}

	public static void quitDriver() {
		WebDriver driver = DRIVER.get();

		if (driver != null) {
			driver.quit();
			DRIVER.remove();
		}
	}
}
