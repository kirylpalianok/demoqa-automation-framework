package com.demoqa.automation.api.client;

import com.demoqa.automation.api.infrastructure.rest.ApiSpecification;
import com.demoqa.automation.api.transport.model.request.LoginRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AccountClient {

	private static final String LOGIN_ENDPOINT = "/Account/v1/Login";
	private static final String TOKEN_ENDPOINT = "/Account/v1/GenerateToken";
	private static final String USER_ENDPOINT = "/Account/v1/User/";

	public Response login(LoginRequest request) {
		return RestAssured
				.given()
				.spec(ApiSpecification.requestSpec())
				.body(request)
				.post(LOGIN_ENDPOINT);
	}

	public Response generateToken(LoginRequest request) {
		return RestAssured
				.given()
				.spec(ApiSpecification.requestSpec())
				.body(request)
				.post(TOKEN_ENDPOINT);
	}

	public Response getUser(String userId, String token) {
		return RestAssured
				.given()
				.spec(ApiSpecification.requestSpec())
				.header("Authorization", "Bearer " + token)
				.get(USER_ENDPOINT + userId);
	}
}
