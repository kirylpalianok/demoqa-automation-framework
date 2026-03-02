package com.demoqa.automation.api.transport.model.response;

import lombok.Data;
import java.util.List;

@Data
public class UserResponse {
	private String userId;
	private String username;
	private List<BookResponse> books;
}
