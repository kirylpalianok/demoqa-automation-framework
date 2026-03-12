package com.demoqa.automation.infrastructure.security;

import com.demoqa.automation.config.ConfigManager;

public class PasswordResolver {

	private static final String SECRET =
			ConfigManager.getConfig().secret();

	public static String resolveApiPassword() {

		return decrypt(ConfigManager.getConfig().encryptedPassword());
	}

	public static String resolveDbPassword() {

		return decrypt(ConfigManager.getConfig().encryptedDbPassword());
	}

	private static String decrypt(String encrypted) {

		if (SECRET == null || SECRET.isBlank()) {
			throw new RuntimeException(
					"Secret key is not provided. Set test.secret property."
			);
		}

		return CryptoUtil.decrypt(encrypted, SECRET);
	}
}