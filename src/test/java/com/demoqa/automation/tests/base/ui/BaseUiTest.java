package com.demoqa.automation.tests.base.ui;

import com.demoqa.automation.config.ConfigManager;
import com.demoqa.automation.ui.driver.DriverManager;
import com.demoqa.automation.ui.listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.time.Duration;

@Listeners({
		io.qameta.allure.testng.AllureTestNg.class,
		TestListener.class
})
public abstract class BaseUiTest {

	@BeforeMethod
	public void setUpDriver() {
		DriverManager.initDriver();

		WebDriver driver = DriverManager.getDriver();

		driver.manage().timeouts().implicitlyWait(
				Duration.ofSeconds(ConfigManager.getConfig().implicitTimeout())
		);

		driver.manage().timeouts().pageLoadTimeout(
				Duration.ofSeconds(ConfigManager.getConfig().pageLoadTimeout())
		);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDownDriver() {
		DriverManager.quitDriver();
	}
}
