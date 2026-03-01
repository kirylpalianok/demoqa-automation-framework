package com.demoqa.automation.api.transport.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {
	private String userName;
	private String password;
}
