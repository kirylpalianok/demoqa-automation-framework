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

	@Key("db.url")
	String dbUrl();

	@Key("db.user")
	String dbUser();

	@Key("db.password.encrypted")
	String encryptedDbPassword();

	@Key("browser")
    String browser();

	@Key("headless")
	boolean headless();

	@Key("implicit.timeout")
	int implicitTimeout();

	@Key("page.load.timeout")
	int pageLoadTimeout();

	@Key("explicit.timeout")
	int explicitTimeout();
}