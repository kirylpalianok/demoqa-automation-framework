package com.demoqa.automation.tests.tests.ui;

import com.demoqa.automation.infrastructure.database.model.TestUser;
import com.demoqa.automation.infrastructure.database.repository.TestDataRepository;
import com.demoqa.automation.tests.base.ui.BaseUiTest;
import com.demoqa.automation.ui.pages.BookStorePage;
import com.demoqa.automation.ui.pages.LoginPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Description;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Epic("DemoQA UI")
@Feature("Book Store")
public class BookStoreTest extends BaseUiTest {

	private final TestDataRepository repository = new TestDataRepository();

	@Test(description = "Verify user can search a book in Book Store")
	@Story("Search Book")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify that user can find a specific book in Book Store search")
	public void userCanSearchBook() {

		TestUser user = repository.getTestUser("admin6");

		LoginPage loginPage = new LoginPage();

		loginPage.open();
		loginPage.login(user.getUsername(), user.getPassword());

		Assert.assertTrue(loginPage.isOnProfilePage());

		BookStorePage bookStorePage = new BookStorePage();

		bookStorePage.open();

		Assert.assertTrue(bookStorePage.isOnBookStorePage());

		bookStorePage.searchBook("Programming JavaScript Applications");

		List<String> books = bookStorePage.getDisplayedBooks();

		Assert.assertEquals(books.size(), 1);

		Assert.assertEquals(
				books.get(0),
				"Programming JavaScript Applications"
		);
	}
}
