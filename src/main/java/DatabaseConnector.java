package main.java;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
	public void connect(String url) {
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(url, "essggmgpyritgn", "226d5952c81905ab5d6e176ac53e686d7fa41d25aece4f631cdebe78855a6dc8");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}
}
