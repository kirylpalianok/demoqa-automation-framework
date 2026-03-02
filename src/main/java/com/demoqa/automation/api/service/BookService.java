package com.demoqa.automation.api.service;

import com.demoqa.automation.api.client.AccountClient;
import com.demoqa.automation.api.client.BookStoreClient;
import com.demoqa.automation.api.transport.model.request.AddBookRequest;
import com.demoqa.automation.api.transport.model.response.BookListResponse;
import com.demoqa.automation.api.transport.model.response.BookResponse;
import com.demoqa.automation.api.transport.model.response.UserResponse;
import io.restassured.response.Response;

import java.util.List;

public class BookService {

	private final BookStoreClient bookStoreClient;
	private final AccountClient accountClient;

	public BookService(BookStoreClient bookStoreClient,
	                   AccountClient accountClient) {
		this.bookStoreClient = bookStoreClient;
		this.accountClient = accountClient;
	}

	public List<BookResponse> getAvailableBooks() {

		Response response = bookStoreClient.getBooks();

		BookListResponse bookList =
				response.as(BookListResponse.class);

		return bookList.getBooks();
	}

	public String addFirstAvailableBook(String userId, String token) {

		Response booksResponse = bookStoreClient.getBooks();

		BookListResponse books =
				booksResponse.as(BookListResponse.class);

		String isbn = books.getBooks().get(0).getIsbn();

		AddBookRequest request = new AddBookRequest(
				userId,
				List.of(new AddBookRequest.Isbn(isbn))
		);

		bookStoreClient.addBook(request, token);

		return isbn;
	}

	public void deleteAllBooks(String userId, String token) {
		bookStoreClient.deleteAllBooks(userId, token);
	}

	public UserResponse getUser(String userId, String token) {

		Response response =
				accountClient.getUser(userId, token);

		return response.as(UserResponse.class);
	}
}