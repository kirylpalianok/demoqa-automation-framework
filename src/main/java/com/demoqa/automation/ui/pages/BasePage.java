package com.demoqa.automation.ui.pages;

import com.demoqa.automation.ui.actions.ElementActions;
import com.demoqa.automation.ui.driver.DriverManager;
import com.demoqa.automation.ui.waits.WaitManager;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

	protected final ElementActions actions;
	protected final WebDriver driver;

	protected BasePage() {
		this.driver = DriverManager.getDriver();
		this.actions = new ElementActions();
	}

	protected void open(String url) {
		driver.get(url);
		WaitManager.waitForPageLoaded();
	}

	protected String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
}
