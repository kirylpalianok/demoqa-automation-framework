package com.demoqa.automation.api.client;

import com.demoqa.automation.api.base.ApiSpecification;
import com.demoqa.automation.api.models.request.LoginRequest;
import com.demoqa.automation.api.models.response.LoginResponse;
import com.demoqa.automation.api.models.response.TokenResponse;
import io.qameta.allure.Step;
import io.restassured.RestAssured;

public class AccountClient {

	private static final String LOGIN_ENDPOINT = "/Account/v1/Login";
	private static final String TOKEN_ENDPOINT = "/Account/v1/GenerateToken";

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

	@Step("Login with username: {request.userName}")
	public LoginResponse loginWithAllure(LoginRequest request) {
		return RestAssured
				.given()
				.spec(ApiSpecification.requestSpecWithAllure())
				.body(request)
				.post(LOGIN_ENDPOINT)
				.then()
				.extract()
				.as(LoginResponse.class);
	}

	@Step("Generate token for user")
	public TokenResponse generateTokenWithAllure(LoginRequest request) {
		return RestAssured
				.given()
				.spec(ApiSpecification.requestSpecWithAllure())
				.body(request)
				.when()
				.post(TOKEN_ENDPOINT)
				.then()
				.extract()
				.as(TokenResponse.class);
	}
}
