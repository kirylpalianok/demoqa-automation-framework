package com.demoqa.automation.api.infrastructure.security;

public class SecretResolver {
	public static String resolveSecret() {

		String secret = System.getenv("TEST_SECRET");

		if (secret == null) {
			secret = System.getProperty("test.secret");
		}

		if (secret == null) {
			throw new RuntimeException(
					"Secret key not provided. Set TEST_SECRET env variable."
			);
		}

		return secret;
	}
}
