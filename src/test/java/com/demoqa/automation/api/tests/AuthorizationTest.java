package com.demoqa.automation.api.tests;

import com.demoqa.automation.api.infrastructure.BaseApiTest;
import com.demoqa.automation.api.transport.model.request.LoginRequest;
import com.demoqa.automation.api.transport.model.response.LoginResponse;
import com.demoqa.automation.api.transport.model.response.TokenResponse;
import com.demoqa.automation.config.ConfigManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Description;

@Epic("DemoQA API")
@Feature("Authentication")
public class AuthorizationTest extends BaseApiTest {

	@Test(description = "Verify user login is successful")
	@Story("User Login")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify that user can login with valid credentials")
	public void testLogin() {

		LoginRequest request = new LoginRequest(
				ConfigManager.getConfig().username(),
				ConfigManager.getConfig().password()
		);

		LoginResponse response = accountClient.login(request);

		Assert.assertNotNull(response.getUserId());
		Assert.assertEquals(response.getUsername(),
				ConfigManager.getConfig().username());
	}

	@Test(description = "Verify token generation is successful")
	@Story("Token Generation")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify that token is generated for valid user")
	public void testGenerateToken() {

		LoginRequest request = new LoginRequest(
				ConfigManager.getConfig().username(),
				ConfigManager.getConfig().password()
		);

		TokenResponse response = accountClient.generateToken(request);

		Assert.assertNotNull(response.getToken());
		Assert.assertEquals(response.getStatus(), "Success");
	}
}
