package com.demoqa.automation.api.transport.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AddBookRequest {
	private String userId;
	private List<Isbn> collectionOfIsbns;

	@Data
	@AllArgsConstructor
	public static class Isbn {
		private String isbn;
	}
}