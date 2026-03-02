package com.demoqa.automation.api.infrastructure.security;

import com.demoqa.automation.config.ConfigManager;

public class PasswordResolver {

	public static String resolve() {

		String encrypted =
				ConfigManager.getConfig().encryptedPassword();

		String secret = SecretResolver.resolveSecret();

		return CryptoUtil.decrypt(encrypted, secret);
	}
}