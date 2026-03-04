package com.demoqa.automation.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface TestConfig extends Config {

	@Key("base.url")
	String baseUrl();

	@Key("username")
	String username();

	@Key("password.encrypted")
	String encryptedPassword();

	@Key("test.secret")
	String secret();
}