package com.demoqa.automation.tests.tests.api;

import com.demoqa.automation.api.domain.auth.AuthData;
import com.demoqa.automation.tests.base.api.BaseApiTest;
import com.demoqa.automation.api.transport.model.request.LoginRequest;
import com.demoqa.automation.api.transport.model.response.TokenResponse;
import com.demoqa.automation.config.ConfigManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Description;

@Epic("DemoQA API")
@Feature("Authorization")
public class AuthorizationTest extends BaseApiTest {

	@Test(description = "Verify user login is successful")
	@Story("User Login")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify that user can login with valid credentials")
	public void testLogin() {

		AuthData auth = authService.loginDefaultUser();

		Assert.assertNotNull(auth.getUserId());
		Assert.assertNotNull(auth.getToken());
	}


	@Test(description = "Verify generateToken endpoint returns Failed status")
	@Story("Negative - Generate Token HTTP")
	@Severity(SeverityLevel.NORMAL)
	public void testGenerateTokenEndpointWithInvalidPassword() {

		LoginRequest request = new LoginRequest(
				ConfigManager.getConfig().username(),
				"wrongPassword123"
		);

		Response response = accountClient.generateToken(request);

		Assert.assertEquals(response.statusCode(), 200);

		TokenResponse tokenResponse =
				response.as(TokenResponse.class);

		Assert.assertEquals(tokenResponse.getStatus(), "Failed");
		Assert.assertNull(tokenResponse.getToken());
		Assert.assertEquals(tokenResponse.getResult(),
				"User authorization failed.");
	}
}
