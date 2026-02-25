package com.demoqa.automation.api.client;

import com.demoqa.automation.api.base.ApiSpecification;
import com.demoqa.automation.api.models.request.AddBookRequest;
import com.demoqa.automation.api.models.response.BookListResponse;
import io.qameta.allure.Step;
import io.restassured.RestAssured;

public class BookStoreClient {

	private static final String BOOKS_ENDPOINT = "/BookStore/v1/Books";

	@Step("Add book to user collection")
	public void addBook(AddBookRequest request, String token) {
		RestAssured
				.given()
				.spec(ApiSpecification.requestSpecWithAllure())
				.header("Authorization", "Bearer " + token)
				.body(request)
				.when()
				.post(BOOKS_ENDPOINT)
				.then()
				.statusCode(201);
	}

	@Step("Delete all books for user")
	public void deleteAllBooks(String userId, String token) {
		RestAssured
				.given()
				.spec(ApiSpecification.requestSpecWithAllure())
				.header("Authorization", "Bearer " + token)
				.queryParam("UserId", userId)
				.when()
				.delete(BOOKS_ENDPOINT)
				.then()
				.statusCode(204);
	}

	@Step("Get all books")
	public BookListResponse getBooks() {
		return RestAssured
				.given()
				.spec(ApiSpecification.requestSpecWithAllure())
				.when()
				.get("/BookStore/v1/Books")
				.then()
				.extract()
				.as(BookListResponse.class);
	}
}