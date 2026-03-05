package com.demoqa.automation.api.infrastructure.database;

import com.demoqa.automation.config.ConfigManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbClient {

	public Connection getConnection() {

		try {
			return DriverManager.getConnection(
					ConfigManager.getConfig().dbUrl(),
					ConfigManager.getConfig().dbUser(),
					ConfigManager.getConfig().dbPassword()
			);
		} catch (SQLException e) {
			throw new RuntimeException("Failed to connect to database", e);
		}
	}
}
