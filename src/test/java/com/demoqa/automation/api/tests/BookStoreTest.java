package com.demoqa.automation.api.tests;

import com.demoqa.automation.api.domain.auth.AuthData;
import com.demoqa.automation.api.domain.user.User;
import com.demoqa.automation.api.infrastructure.BaseApiTest;
import com.demoqa.automation.api.transport.model.request.AddBookRequest;
import com.demoqa.automation.api.transport.model.response.BookListResponse;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

@Epic("DemoQA API")
@Feature("Book Store")
public class BookStoreTest extends BaseApiTest {

	@BeforeMethod
	public void cleanUserBooks() {
		userFactory.defaultUser()
				.login()
				.deleteAllBooks();
	}

	@Test(description = "Verify books list is not empty")
	@Story("Get Books")
	@Severity(SeverityLevel.NORMAL)
	public void testGetBooks() {

		User user = userFactory.defaultUser().login();

		boolean hasBooks = user.getAvailableBooks().size() > 0;

		Assert.assertTrue(hasBooks);
	}

	@Test(description = "Verify authorized user can add a book")
	@Story("Add Book")
	@Severity(SeverityLevel.CRITICAL)
	public void testAddBook() {

		User user = userFactory.defaultUser()
				.login()
				.addFirstAvailableBook();

		Assert.assertTrue(
				user.hasBook(user.getLastAddedBookIsbn())
		);
	}

	@Test(description = "Verify authorized user can delete all books")
	@Story("Delete Books")
	@Severity(SeverityLevel.CRITICAL)
	public void testDeleteBooks() {

		User user = userFactory.defaultUser().login();

		user.addFirstAvailableBook()
				.deleteAllBooks();

		Assert.assertTrue(user.getUserBooks().isEmpty());
	}

	@Test(description = "Verify user cannot add book with invalid token")
	@Story("Negative - Add Book")
	@Severity(SeverityLevel.CRITICAL)
	public void testAddBookWithInvalidToken() {

		AuthData auth = authService.loginDefaultUser();
		String userId = auth.getUserId();

		Response booksResponse = bookStoreClient.getBooks();

		Assert.assertEquals(booksResponse.statusCode(), 200);

		BookListResponse books =
				booksResponse.as(BookListResponse.class);

		Assert.assertNotNull(books.getBooks(), "Books list is null");
		Assert.assertFalse(books.getBooks().isEmpty(), "Books list is empty");

		String isbn = books.getBooks().get(0).getIsbn();

		AddBookRequest request = new AddBookRequest(
				userId,
				List.of(new AddBookRequest.Isbn(isbn))
		);

		String invalidToken = "invalid_token_123";

		Response response =
				bookStoreClient.addBook(request, invalidToken);

		Assert.assertEquals(response.statusCode(), 401);

		String body = response.asString();
		Assert.assertTrue(body.contains("User not authorized!"));
	}
}