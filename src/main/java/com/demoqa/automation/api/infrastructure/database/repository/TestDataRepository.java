package com.demoqa.automation.api.infrastructure.database.repository;

import com.demoqa.automation.api.infrastructure.database.DbClient;
import com.demoqa.automation.api.infrastructure.database.model.TestUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestDataRepository {

	private final DbClient dbClient = new DbClient();

	public TestUser getTestUser(String username) {

		String query =
				"SELECT username, password, books FROM public.data WHERE username = ? LIMIT 1";

		try (
				Connection conn = dbClient.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)
		) {

			stmt.setString(1, username);

			try (ResultSet rs = stmt.executeQuery()) {

				if (rs.next()) {

					String dbUsername = rs.getString("username");
					String password = rs.getString("password");
					String book = rs.getString("books");

					if (dbUsername == null || password == null) {
						throw new RuntimeException("Invalid test data in DB");
					}

					return new TestUser(dbUsername, password, book);
				}

			}

		} catch (Exception e) {
			throw new RuntimeException("Failed to fetch test data", e);
		}

		throw new RuntimeException("No test data found in DB for username: " + username);
	}
}
