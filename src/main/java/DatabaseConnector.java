package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
	public void executeUpdate(String statement) throws SQLException, ClassNotFoundException {
		Connection c = null;
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://ec2-52-23-14-156.compute-1.amazonaws.com:5432/dbo4aa6j9pt62c";
		c = DriverManager.getConnection(url, "essggmgpyritgn",
				"226d5952c81905ab5d6e176ac53e686d7fa41d25aece4f631cdebe78855a6dc8");
		c.setAutoCommit(false);
		Statement s = c.createStatement();
		s.executeUpdate(statement);
		c.commit();
		s.close();
		c.close();
		System.out.println("Opened database successfully");
	}

	public ResultSet executeQuery(String statement) throws SQLException, ClassNotFoundException {
		Connection c = null;
		Statement s = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://ec2-52-23-14-156.compute-1.amazonaws.com:5432/dbo4aa6j9pt62c";
			c = DriverManager.getConnection(url, "essggmgpyritgn",
					"226d5952c81905ab5d6e176ac53e686d7fa41d25aece4f631cdebe78855a6dc8");
			c.setAutoCommit(false);
			s = c.createStatement();
			return s.executeQuery(statement);
		} finally {
			s.close();
			c.close();
			System.out.println("Opened database successfully");
		}
	}
}
