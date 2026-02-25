package com.demoqa.automation.api.models.response;

import lombok.Data;
import java.util.List;

@Data
public class BookListResponse {
	private List<BookResponse> books;
}
