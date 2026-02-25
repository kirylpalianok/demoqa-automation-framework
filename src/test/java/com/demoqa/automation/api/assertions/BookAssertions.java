package com.demoqa.automation.api.assertions;

import com.demoqa.automation.api.models.response.UserResponse;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.List;

public class BookAssertions {

	@Step("Verify books list is not empty")
	public static void assertBooksNotEmpty(List<?> books) {
		Assert.assertNotNull(books, "Books list is null");
		Assert.assertTrue(!books.isEmpty(), "Books list is empty");
	}

	@Step("Verify book is present in user collection")
	public static void assertBookPresent(UserResponse user, String isbn) {
		Assert.assertTrue(
				user.getBooks().stream()
						.anyMatch(book -> book.getIsbn().equals(isbn)),
				"Book was not added to user collection"
		);
	}

	@Step("Verify user books are empty")
	public static void assertUserBooksEmpty(UserResponse user) {
		Assert.assertTrue(
				user.getBooks().isEmpty(),
				"User books were not deleted"
		);
	}
}