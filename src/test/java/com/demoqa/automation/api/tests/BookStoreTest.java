package com.demoqa.automation.api.tests;

import com.demoqa.automation.api.base.BaseApiTest;
import com.demoqa.automation.api.models.request.AddBookRequest;
import com.demoqa.automation.api.models.response.BookListResponse;
import com.demoqa.automation.api.models.response.UserResponse;
import com.demoqa.automation.api.assertions.BookAssertions;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

@Epic("DemoQA API")
@Feature("Book Store")
public class BookStoreTest extends BaseApiTest {

	@BeforeMethod
	public void cleanUserBooks() {
		bookStoreClient.deleteAllBooks(userId, token);
	}

	@Test(description = "Verify books list is not empty")
	@Story("Get Books")
	@Severity(SeverityLevel.NORMAL)
	public void testGetBooks() {

		BookListResponse response = bookStoreClient.getBooks();

		BookAssertions.assertBooksNotEmpty(response.getBooks());
	}

	@Test(description = "Verify authorized user can add a book")
	@Story("Add Book")
	@Severity(SeverityLevel.CRITICAL)
	public void testAddBook() {

		String isbn = bookStoreClient.getBooks()
				.getBooks()
				.get(0)
				.getIsbn();

		AddBookRequest request = new AddBookRequest(
				userId,
				List.of(new AddBookRequest.Isbn(isbn))
		);

		bookStoreClient.addBook(request, token);

		UserResponse user = accountClient.getUser(userId, token);

		BookAssertions.assertBookPresent(user, isbn);
	}

	@Test(description = "Verify authorized user can delete all books")
	@Story("Delete Books")
	@Severity(SeverityLevel.CRITICAL)
	public void testDeleteBooks() {

		String isbn = bookStoreClient.getBooks()
				.getBooks()
				.get(0)
				.getIsbn();

		AddBookRequest request = new AddBookRequest(
				userId,
				List.of(new AddBookRequest.Isbn(isbn))
		);

		bookStoreClient.addBook(request, token);

		bookStoreClient.deleteAllBooks(userId, token);

		UserResponse user = accountClient.getUser(userId, token);

		BookAssertions.assertUserBooksEmpty(user);
	}
}