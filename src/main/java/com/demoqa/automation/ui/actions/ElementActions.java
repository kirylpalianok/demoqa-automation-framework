package com.demoqa.automation.ui.actions;

import com.demoqa.automation.ui.waits.WaitManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ElementActions {

	@Step("Click element {locator}")
	public void click(By locator) {

		WebElement element = WaitManager.waitForClickable(locator);
		element.click();
	}

	@Step("Click element {locator}")
	public void type(By locator, String text) {

		WebElement element = WaitManager.waitForVisible(locator);
		element.clear();
		element.sendKeys(text);
	}

	public String getText(By locator) {

		WebElement element = WaitManager.waitForVisible(locator);
		return element.getText();
	}

	public void clear(By locator) {

		WebElement element = WaitManager.waitForVisible(locator);
		element.clear();
	}

	public boolean isDisplayed(By locator) {

		WebElement element = WaitManager.waitForPresence(locator);
		return element.isDisplayed();
	}

	public void selectByVisibleText(By locator, String text) {

		WebElement element = WaitManager.waitForClickable(locator);
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
}
