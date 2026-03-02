package com.demoqa.automation.api.infrastructure.rest;

import com.demoqa.automation.config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;

public class ApiSpecification {

	private static final String BASE_URL =
			ConfigManager.getConfig().baseUrl();

	public static RequestSpecification requestSpec() {
		return new RequestSpecBuilder()
				.setBaseUri(BASE_URL)
				.setContentType(ContentType.JSON)
				.build();
	}
}