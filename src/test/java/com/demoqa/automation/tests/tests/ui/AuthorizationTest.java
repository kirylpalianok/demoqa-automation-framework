package com.demoqa.automation.tests.tests.ui;

import com.demoqa.automation.infrastructure.database.model.TestUser;
import com.demoqa.automation.infrastructure.database.repository.TestDataRepository;
import com.demoqa.automation.tests.base.ui.BaseUiTest;
import com.demoqa.automation.ui.pages.LoginPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("DemoQA UI")
@Feature("Authentication")
public class AuthorizationTest extends BaseUiTest {

	private final TestDataRepository repository = new TestDataRepository();
	private static final String INVALID_PASSWORD = "wrongPassword123";

	@Test(description = "Verify user can login with valid credentials")
	@Story("User Login")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify that user can login and is redirected to profile page")
	public void userCanLoginWithValidCredentials() {

		TestUser user = repository.getTestUser("admin6");

		LoginPage loginPage = new LoginPage();

		loginPage.open();
		loginPage.login(user.getUsername(), user.getPassword());

		Assert.assertTrue(loginPage.isOnProfilePage(),
				"User was not redirected to Profile page");

		String loggedUser = loginPage.getLoggedUser();

		Assert.assertEquals(loggedUser, user.getUsername());
	}

	@Test(description = "Verify user cannot login with invalid password")
	@Story("User Login")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify that user cannot login and is remained on login page")
	public void userCannotLoginWithInvalidPassword() {

		TestUser user = repository.getTestUser("admin6");

		LoginPage loginPage = new LoginPage();

		loginPage.open();

		loginPage.login(user.getUsername(), INVALID_PASSWORD);

		Assert.assertTrue(loginPage.isErrorDisplayed(),
				"Error message is not displayed");

		Assert.assertEquals(
				loginPage.getErrorMessage(),
				"Invalid username or password!"
		);

		Assert.assertTrue(loginPage.isOnLoginPage(),
				"User should remain on login page after failed login");
	}
}
