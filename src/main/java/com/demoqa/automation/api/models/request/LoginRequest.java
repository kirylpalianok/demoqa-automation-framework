package com.demoqa.automation.api.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {
	private String userName;
	private String password;
}
