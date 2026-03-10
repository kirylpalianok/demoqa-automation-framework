package com.demoqa.automation.api.service;

import com.demoqa.automation.api.client.AccountClient;
import com.demoqa.automation.api.domain.auth.AuthData;
import com.demoqa.automation.api.infrastructure.security.PasswordResolver;
import com.demoqa.automation.api.transport.model.request.LoginRequest;
import com.demoqa.automation.api.transport.model.response.LoginResponse;
import com.demoqa.automation.api.transport.model.response.TokenResponse;
import com.demoqa.automation.config.ConfigManager;
import io.restassured.response.Response;

public class AuthService {

	private final AccountClient accountClient;

	public AuthService(AccountClient accountClient) {
		this.accountClient = accountClient;
	}

	public AuthData loginDefaultUser() {

		String username = ConfigManager.getConfig().username();
		String password = PasswordResolver.resolveApiPassword();

		return login(username, password);
	}

	public AuthData login(String username, String password) {

		LoginRequest request = new LoginRequest(username, password);

		Response loginResponseRaw = accountClient.login(request);
		Response tokenResponseRaw = accountClient.generateToken(request);

		LoginResponse loginResponse =
				loginResponseRaw.as(LoginResponse.class);

		TokenResponse tokenResponse =
				tokenResponseRaw.as(TokenResponse.class);

		return new AuthData(
				loginResponse.getUserId(),
				tokenResponse.getToken()
		);
	}
}