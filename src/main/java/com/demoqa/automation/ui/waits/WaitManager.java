package com.demoqa.automation.ui.waits;

import com.demoqa.automation.config.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.demoqa.automation.ui.driver.DriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitManager {

	private WaitManager() {}

	private static WebDriverWait getWait() {

		WebDriver driver = DriverManager.getDriver();

		return new WebDriverWait(
				driver,
				Duration.ofSeconds(ConfigManager.getConfig().explicitTimeout())
		);
	}

	public static WebElement waitForVisible(By locator) {

		return getWait().until(
				ExpectedConditions.visibilityOfElementLocated(locator)
		);
	}

	public static WebElement waitForClickable(By locator) {

		return getWait().until(
				ExpectedConditions.elementToBeClickable(locator)
		);
	}

	public static WebElement waitForPresence(By locator) {

		return getWait().until(
				ExpectedConditions.presenceOfElementLocated(locator)
		);
	}

	public static boolean waitForText(By locator, String text) {

		return getWait().until(
				ExpectedConditions.textToBe(locator, text)
		);
	}

	public static boolean waitForInvisibility(By locator) {

		return getWait().until(
				ExpectedConditions.invisibilityOfElementLocated(locator)
		);
	}

	public static void waitForPageLoaded() {

		getWait().until(driver ->
				((JavascriptExecutor) driver)
						.executeScript("return document.readyState")
						.equals("complete")
		);
	}

	public static void waitForUrlContains(String text) {
		new WebDriverWait(
				DriverManager.getDriver(),
				Duration.ofSeconds(ConfigManager.getConfig().explicitTimeout())
		).until(ExpectedConditions.urlContains(text));
	}
}
