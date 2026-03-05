package com.demoqa.automation.api.infrastructure.database.repository;

import com.demoqa.automation.api.infrastructure.database.DbClient;
import com.demoqa.automation.api.infrastructure.database.model.TestUser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDataRepository {

	private final DbClient dbClient = new DbClient();

	public TestUser getTestUser() {

		String query = "SELECT username, password, books FROM public.data WHERE username = 'admin6' LIMIT 1";

		try (
				Connection conn = dbClient.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)
		) {

			if (rs.next()) {
				return new TestUser(
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("books")
				);
			}

		} catch (Exception e) {
			throw new RuntimeException("Failed to fetch test data", e);
		}

		throw new RuntimeException("No test data found in DB");
	}
}
