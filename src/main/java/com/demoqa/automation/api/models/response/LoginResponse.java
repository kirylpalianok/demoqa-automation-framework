package com.demoqa.automation.api.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {
	private String userId;
	private String username;
	private String token;
	private String expires;
}