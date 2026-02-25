package com.demoqa.automation.api.assertions;

import com.demoqa.automation.api.models.response.LoginResponse;
import com.demoqa.automation.api.models.response.TokenResponse;
import org.testng.Assert;

public class AccountAssertions {

	public static void assertLoginSuccessful(LoginResponse response, String expectedUsername) {
		Assert.assertNotNull(response, "Login response is null");
		Assert.assertNotNull(response.getUserId(), "UserId is null");
		Assert.assertEquals(response.getUsername(), expectedUsername,
				"Username does not match expected value");
	}

	public static void assertTokenGenerated(TokenResponse response) {
		Assert.assertNotNull(response, "Token response is null");
		Assert.assertNotNull(response.getToken(), "Token is null");
		Assert.assertEquals(response.getStatus(), "Success",
				"Token generation status is not Success");
	}
}