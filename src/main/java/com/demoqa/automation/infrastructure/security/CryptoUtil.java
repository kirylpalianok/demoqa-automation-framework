package com.demoqa.automation.infrastructure.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CryptoUtil {

	private static final String ALGORITHM = "AES";

	private CryptoUtil() {}

	public static String encrypt(String plainText, String secret) {
		try {
			SecretKeySpec key = buildKey(secret);

			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] encrypted = cipher.doFinal(
					plainText.getBytes(StandardCharsets.UTF_8)
			);

			return Base64.getEncoder().encodeToString(encrypted);

		} catch (Exception e) {
			throw new RuntimeException("Encryption failed", e);
		}
	}

	public static String decrypt(String encryptedText, String secret) {
		try {
			SecretKeySpec key = buildKey(secret);

			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);

			byte[] decoded = Base64.getDecoder().decode(encryptedText);

			byte[] decrypted = cipher.doFinal(decoded);

			return new String(decrypted, StandardCharsets.UTF_8);

		} catch (Exception e) {
			throw new RuntimeException("Decryption failed", e);
		}
	}

	private static SecretKeySpec buildKey(String secret) {
		byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
		byte[] key = new byte[16];

		System.arraycopy(keyBytes, 0, key, 0,
				Math.min(keyBytes.length, 16));

		return new SecretKeySpec(key, ALGORITHM);
	}
}
