package com.demoqa.automation.api.models.response;

import lombok.Data;

@Data
public class TokenResponse {
	private String token;
	private String expires;
	private String status;
	private String result;
}
