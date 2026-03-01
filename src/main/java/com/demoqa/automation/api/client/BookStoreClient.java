package com.demoqa.automation.api.client;

import com.demoqa.automation.api.infrastructure.rest.ApiSpecification;
import com.demoqa.automation.api.transport.model.request.AddBookRequest;
import com.demoqa.automation.api.transport.model.response.BookListResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BookStoreClient {

	private static final String BOOKS_ENDPOINT = "/BookStore/v1/Books";

	public Response addBook(AddBookRequest request, String token) {
		return RestAssured
				.given()
				.spec(ApiSpecification.requestSpec())
				.header("Authorization", "Bearer " + token)
				.body(request)
				.post(BOOKS_ENDPOINT);
	}

	public Response deleteAllBooks(String userId, String token) {
		return RestAssured
				.given()
				.spec(ApiSpecification.requestSpec())
				.header("Authorization", "Bearer " + token)
				.queryParam("UserId", userId)
				.delete(BOOKS_ENDPOINT);
	}

	public BookListResponse getBooks() {
		return RestAssured
				.given()
				.spec(ApiSpecification.requestSpec())
				.get(BOOKS_ENDPOINT)
				.then()
				.extract()
				.as(BookListResponse.class);
	}
}