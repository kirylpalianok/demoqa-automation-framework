package com.demoqa.automation.api.infrastructure.database.model;

public class TestUser {

	private final String username;
	private final String password;
	private final String book;

	public TestUser(String username, String password, String book) {
		this.username = username;
		this.password = password;
		this.book = book;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getBook() {
		return book;
	}
}
