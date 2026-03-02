package com.demoqa.automation.api.client;

import com.demoqa.automation.api.infrastructure.rest.ApiSpecification;
import com.demoqa.automation.api.transport.model.request.LoginRequest;
import com.demoqa.automation.api.transport.model.response.LoginResponse;
import com.demoqa.automation.api.transport.model.response.TokenResponse;
import com.demoqa.automation.api.transport.model.response.UserResponse;
import io.restassured.RestAssured;

public class AccountClient {

	private static final String LOGIN_ENDPOINT = "/Account/v1/Login";
	private static final String TOKEN_ENDPOINT = "/Account/v1/GenerateToken";
	private static final String USER_ENDPOINT = "/Account/v1/User/";

	public LoginResponse login(LoginRequest request) {
		return RestAssured
				.given()
				.spec(ApiSpecification.requestSpec())
				.body(request)
				.post(LOGIN_ENDPOINT)
				.then()
				.extract()
				.as(LoginResponse.class);
	}

	public TokenResponse generateToken(LoginRequest request) {
		return RestAssured
				.given()
				.spec(ApiSpecification.requestSpec())
				.body(request)
				.when()
				.post(TOKEN_ENDPOINT)
				.then()
				.extract()
				.as(TokenResponse.class);
	}

	public UserResponse getUser(String userId, String token) {
		return RestAssured
				.given()
				.spec(ApiSpecification.requestSpec())
				.header("Authorization", "Bearer " + token)
				.when()
				.get(USER_ENDPOINT + userId)
				.then()
				.extract()
				.as(UserResponse.class);
	}
}
