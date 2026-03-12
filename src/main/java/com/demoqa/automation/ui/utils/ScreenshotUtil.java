package com.demoqa.automation.ui.utils;

import com.demoqa.automation.ui.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

	public static byte[] takeScreenshot() {

		WebDriver driver = DriverManager.getDriver();

		if (driver == null) {
			return null;
		}

		return ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.BYTES);
	}
}
