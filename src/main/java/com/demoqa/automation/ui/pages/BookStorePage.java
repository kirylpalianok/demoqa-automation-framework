package com.demoqa.automation.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BookStorePage extends BasePage {

	private static final String PAGE_URL = "https://demoqa.com/books";

	private final By searchInput = By.id("searchBox");
	private final By bookTitles = By.cssSelector(".action-buttons a");

	@Step("Open Book Store page")
	public void open() {
		super.open(PAGE_URL);
	}

	@Step("Search book with name: {bookName}")
	public void searchBook(String bookName) {
		actions.type(searchInput, bookName);
	}

	@Step("Get displayed books")
	public List<String> getDisplayedBooks() {

		return driver.findElements(bookTitles)
				.stream()
				.map(WebElement::getText)
				.toList();
	}

	@Step("Verify Book Store page is opened")
	public boolean isOnBookStorePage() {
		return getCurrentUrl().contains("/books");
	}

}
