package com.demoqa.automation.api.base;

import com.demoqa.automation.api.client.AccountClient;
import com.demoqa.automation.api.models.request.LoginRequest;
import com.demoqa.automation.api.models.response.LoginResponse;
import com.demoqa.automation.api.models.response.TokenResponse;
import com.demoqa.automation.config.ConfigManager;
import org.testng.annotations.BeforeClass;
import com.demoqa.automation.api.client.BookStoreClient;

public class BaseApiTest {

	protected AccountClient accountClient;
	protected String token;
	protected String userId;
	protected BookStoreClient bookStoreClient;


	@BeforeClass
	public void setUp() {
		accountClient = new AccountClient();
		bookStoreClient = new BookStoreClient();

		LoginRequest request = new LoginRequest(
				ConfigManager.getConfig().username(),
				ConfigManager.getConfig().password()
		);

		LoginResponse loginResponse = accountClient.login(request);
		userId = loginResponse.getUserId();

		TokenResponse tokenResponse = accountClient.generateToken(request);
		token = tokenResponse.getToken();

		if (token == null || userId == null) {
			throw new RuntimeException("Authentication failed.");
		}
	}
}
