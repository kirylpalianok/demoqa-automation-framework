package com.demoqa.automation.ui.pages;

import com.demoqa.automation.ui.driver.DriverManager;
import com.demoqa.automation.ui.waits.WaitManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

	private static final String PAGE_URL = "https://demoqa.com/login";

	private final By usernameInput = By.id("userName");
	private final By passwordInput = By.id("password");
	private final By loginButton = By.id("login");
	private final By userLabel = By.id("userName-value");
	private final By errorMessage = By.id("name");

	@Step("Open login page")
	public void open() {
		super.open(PAGE_URL);
	}

	public void enterUsername(String username) {
		actions.type(usernameInput, username);
	}

	public void enterPassword(String password) {
		actions.type(passwordInput, password);
	}

	public void clickLogin() {
		actions.click(loginButton);
	}

	@Step("Login as user {username}")
	public void login(String username, String password) {

		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}

	public String getLoggedUser() {
		return actions.getText(userLabel);
	}

	public boolean isOnProfilePage() {
		WaitManager.waitForUrlContains("profile");

		return DriverManager.getDriver()
				.getCurrentUrl()
				.contains("profile");
	}

	public String getErrorMessage() {
		return actions.getText(errorMessage);
	}

	public boolean isErrorDisplayed() {
		return actions.isDisplayed(errorMessage);
	}

	public boolean isOnLoginPage() {
		return getCurrentUrl().contains("/login");
	}
}
