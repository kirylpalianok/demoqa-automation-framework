package com.demoqa.automation.api.tests;

import com.demoqa.automation.api.infrastructure.BaseApiTest;
import com.demoqa.automation.api.infrastructure.database.model.TestUser;
import com.demoqa.automation.api.infrastructure.database.repository.TestDataRepository;
import com.demoqa.automation.api.transport.model.request.LoginRequest;
import com.demoqa.automation.api.transport.model.response.LoginResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("DemoQA API")
@Feature("DB Driven Authorization")
public class DbDrivenAuthorizationTest extends BaseApiTest {

	private final TestDataRepository repository = new TestDataRepository();

	@Test(description = "Verify user login is successful with data from DB")
	@Story("User Login")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify that user can login with valid credentials from DB")
	public void testLoginUsingDatabaseData() {

		TestUser user = repository.getTestUser("admin6");

		LoginRequest request =
				new LoginRequest(user.getUsername(), user.getPassword());

		Response response = accountClient.login(request);

		Assert.assertEquals(response.statusCode(), 200);

		LoginResponse loginResponse =
				response.as(LoginResponse.class);

		Assert.assertEquals(loginResponse.getUsername(), user.getUsername());
		Assert.assertNotNull(loginResponse.getUserId());
		Assert.assertNotNull(loginResponse.getToken());

	}
}
