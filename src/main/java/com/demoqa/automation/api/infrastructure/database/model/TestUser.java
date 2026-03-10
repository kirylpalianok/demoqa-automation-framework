package com.demoqa.automation.api.infrastructure.database.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TestUser {

	private final String username;
	private final String password;
	private final String book;

}
