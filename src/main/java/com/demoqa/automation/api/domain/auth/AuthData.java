package com.demoqa.automation.api.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthData {

	private final String userId;
	private final String token;

}
