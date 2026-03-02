package com.demoqa.automation.api.tests;

import com.demoqa.automation.api.domain.user.User;
import com.demoqa.automation.api.infrastructure.BaseApiTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
}