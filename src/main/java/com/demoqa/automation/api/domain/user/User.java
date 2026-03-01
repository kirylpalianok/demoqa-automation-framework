package com.demoqa.automation.api.domain.user;

import com.demoqa.automation.api.domain.auth.AuthData;
import com.demoqa.automation.api.transport.model.response.BookResponse;
import com.demoqa.automation.api.service.AuthService;
import com.demoqa.automation.api.service.BookService;
import io.qameta.allure.Step;

import java.util.List;

public class User {

	private final String username;
	private final String password;

	private String lastAddedBookIsbn;

	private final AuthService authService;
	private final BookService bookService;

	private String userId;
	private String token;
	private boolean authenticated;

	private User(String username,
	             String password,
	             AuthService authService,
	             BookService bookService) {
		this.username = username;
		this.password = password;
		this.authService = authService;
		this.bookService = bookService;
	}

	public static User withCredentials(String username,
	                                   String password,
	                                   AuthService authService,
	                                   BookService bookService) {
		return new User(username, password, authService, bookService);
	}

	@Step("User logs in with username: {username}")
	public User login() {
		AuthData authData = authService.login(username, password);

		this.userId = authData.getUserId();
		this.token = authData.getToken();
		this.authenticated = true;

		return this;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public String getUserId() {
		return userId;
	}

	public String getToken() {
		return token;
	}

	public List<BookResponse> getAvailableBooks() {
		return bookService.getAvailableBooks();
	}

	public String getUsername() {
		return username;
	}

	@Step("User retrieves own book collection")
	public List<BookResponse> getUserBooks() {
		ensureAuthenticated();
		return bookService.getUser(userId, token).getBooks();
	}

	@Step("User adds first available book")
	public User addFirstAvailableBook() {
		ensureAuthenticated();
		this.lastAddedBookIsbn =
				bookService.addFirstAvailableBook(userId, token);
		return this;
	}

	public String getLastAddedBookIsbn() {
		return lastAddedBookIsbn;
	}

	@Step("User verifies that book with ISBN {isbn} exists in collection")
	public boolean hasBook(String isbn) {
		ensureAuthenticated();
		return bookService.getUser(userId, token)
				.getBooks()
				.stream()
				.anyMatch(book -> book.getIsbn().equals(isbn));
	}

	@Step("User deletes all books")
	public User deleteAllBooks() {
		ensureAuthenticated();
		bookService.deleteAllBooks(userId, token);
		return this;
	}

	private void ensureAuthenticated() {
		if (!authenticated) {
			throw new IllegalStateException("User must be authenticated");
		}
	}
}