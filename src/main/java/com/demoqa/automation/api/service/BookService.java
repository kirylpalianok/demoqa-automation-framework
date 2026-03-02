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
		BookListResponse response = bookStoreClient.getBooks();
		return response.getBooks();
	}

	public String addFirstAvailableBook(String userId, String token) {

		BookListResponse books = bookStoreClient.getBooks();

		String isbn = books.getBooks().get(0).getIsbn();

		AddBookRequest request = new AddBookRequest(
				userId,
				List.of(new AddBookRequest.Isbn(isbn))
		);

		Response response = bookStoreClient.addBook(request, token);

		if (response.statusCode() != 201) {
			throw new RuntimeException("Failed to add book. Status: "
					+ response.statusCode());
		}

		return isbn;
	}

	public void deleteAllBooks(String userId, String token) {
		Response response = bookStoreClient.deleteAllBooks(userId, token);

		if (response.statusCode() != 204) {
			throw new RuntimeException("Failed to delete books. Status: "
					+ response.statusCode());
		}
	}

	public UserResponse getUser(String userId, String token) {
		return accountClient.getUser(userId, token);
	}
}