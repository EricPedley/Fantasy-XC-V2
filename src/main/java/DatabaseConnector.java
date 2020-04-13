package main.java;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class DatabaseConnector {//TODO: add try/catch to both methods instead of throws
	public void executeUpdate(String statement) throws SQLException, ClassNotFoundException {
		Connection c = null;
		Statement s = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://ec2-52-23-14-156.compute-1.amazonaws.com:5432/dbo4aa6j9pt62c";
			c = DriverManager.getConnection(url, "essggmgpyritgn",
					"226d5952c81905ab5d6e176ac53e686d7fa41d25aece4f631cdebe78855a6dc8");
			c.setAutoCommit(false);
			s = c.createStatement();
			s.executeUpdate(statement);
			c.commit();
		} finally {
			s.close();
			c.close();
			try {
				FileWriter f = new FileWriter("dblogs.txt");
				f.append(statement + " | " + (new Date()) + "\n");
				f.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("finished database operation(might not have been successful as this println is in the finally clause");
		}

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
			try {
				FileWriter f = new FileWriter("dblogs.txt");
				f.append(statement + " | " + (new Date()) + "\n");
				f.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("finished database operation(might not have been successful as this println is in the finally clause");
		}
	}
}
