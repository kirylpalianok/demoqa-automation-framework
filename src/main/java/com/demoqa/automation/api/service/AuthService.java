package com.demoqa.automation.api.service;


import com.demoqa.automation.api.client.AccountClient;
import com.demoqa.automation.api.domain.auth.AuthData;
import com.demoqa.automation.api.transport.model.request.LoginRequest;
import com.demoqa.automation.api.transport.model.response.LoginResponse;
import com.demoqa.automation.api.transport.model.response.TokenResponse;

public class AuthService {

	private final AccountClient accountClient;

	public AuthService(AccountClient accountClient) {
		this.accountClient = accountClient;
	}

	public AuthData login(String username, String password) {
		LoginRequest request = new LoginRequest(username, password);

		LoginResponse loginResponse = accountClient.login(request);
		TokenResponse tokenResponse = accountClient.generateToken(request);

		if (loginResponse.getUserId() == null || tokenResponse.getToken() == null) {
			throw new RuntimeException("Authentication failed");
		}

		return new AuthData(
				loginResponse.getUserId(),
				tokenResponse.getToken()
		);
	}
}