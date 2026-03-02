package com.demoqa.automation.api.domain.factory;

import com.demoqa.automation.api.domain.user.User;
import com.demoqa.automation.api.service.AuthService;
import com.demoqa.automation.api.service.BookService;
import com.demoqa.automation.config.ConfigManager;

public class UserFactory {

	private final AuthService authService;
	private final BookService bookService;

	public UserFactory(AuthService authService,
	                   BookService bookService) {
		this.authService = authService;
		this.bookService = bookService;
	}

	public User defaultUser() {
		return User.withCredentials(
				ConfigManager.getConfig().username(),
				ConfigManager.getConfig().password(),
				authService,
				bookService
		);
	}

	public User withCredentials(String username, String password) {
		return User.withCredentials(
				username,
				password,
				authService,
				bookService
		);
	}
}