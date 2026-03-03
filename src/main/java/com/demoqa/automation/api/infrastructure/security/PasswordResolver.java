package com.demoqa.automation.api.infrastructure.security;

import com.demoqa.automation.config.ConfigManager;

public class PasswordResolver {

	public static String resolve() {

		String encrypted =
				ConfigManager.getConfig().encryptedPassword();

		String secret =
				ConfigManager.getConfig().secret();

		if (secret == null || secret.isBlank()) {
			throw new RuntimeException(
					"Secret key is not provided. Set test.secret property."
			);
		}

		return CryptoUtil.decrypt(encrypted, secret);
	}
}