package com.demoqa.automation.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigManager {

	private static final TestConfig CONFIG =
			ConfigFactory.create(TestConfig.class, System.getProperties());

	private ConfigManager() {}

	public static TestConfig getConfig() {
		return CONFIG;
	}
}
