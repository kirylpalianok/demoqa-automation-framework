package com.demoqa.automation.api.infrastructure;

import com.demoqa.automation.api.client.AccountClient;
import com.demoqa.automation.api.client.BookStoreClient;
import com.demoqa.automation.api.dsl.UserFactory;
import com.demoqa.automation.api.service.AuthService;
import com.demoqa.automation.api.service.BookService;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public abstract class BaseApiTest {

	protected AccountClient accountClient;
	protected BookStoreClient bookStoreClient;
	protected AuthService authService;
	protected BookService bookService;
	protected UserFactory userFactory;

	@BeforeSuite
	public void configureRestAssured() {
		RestAssured.filters(new AllureRestAssured());
	}

	@BeforeClass
	public void setUp() {
		accountClient = new AccountClient();
		bookStoreClient = new BookStoreClient();
		authService = new AuthService(accountClient);
		bookService = new BookService(bookStoreClient, accountClient);
		userFactory = new UserFactory(authService, bookService);
	}
}
