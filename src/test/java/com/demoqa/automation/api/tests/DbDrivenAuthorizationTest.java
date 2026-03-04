package com.demoqa.automation.api.tests;

import com.demoqa.automation.api.infrastructure.BaseApiTest;
import com.demoqa.automation.api.infrastructure.database.model.TestUser;
import com.demoqa.automation.api.infrastructure.database.repository.TestDataRepository;
import com.demoqa.automation.api.transport.model.request.LoginRequest;
import com.demoqa.automation.api.transport.model.response.LoginResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DbDrivenAuthorizationTest extends BaseApiTest {

	private final TestDataRepository repository = new TestDataRepository();

	@Test
	public void testLoginUsingDatabaseData() {

		TestUser user = repository.getTestUser();

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
