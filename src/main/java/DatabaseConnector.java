package main.java;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class DatabaseConnector {
	public void executeUpdate(String statement) {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				s.close();
				c.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				FileWriter f = new FileWriter("dblogs.txt",true);
				f.append(statement + " | " + (new Date()) + "\n");
				f.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("finished database operation(might not have been successful as this println is in the finally clause");
		}

	}

	public String executeQuery(String statement)  {
		Connection c = null;
		Statement s = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://ec2-52-23-14-156.compute-1.amazonaws.com:5432/dbo4aa6j9pt62c";
			c = DriverManager.getConnection(url, "essggmgpyritgn",
					"226d5952c81905ab5d6e176ac53e686d7fa41d25aece4f631cdebe78855a6dc8");
			c.setAutoCommit(false);
			s = c.createStatement();
			ResultSet results = s.executeQuery(statement);
			String result = "results was empty";
			if(results.next()) {
				result = results.getString(1);
			}
			results.close();
			System.out.println("database accessed successfully");
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				try {
					c.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO Auto-generated catch block
				FileWriter f;
				try {
					f = new FileWriter("dblogs.txt",true);
					f.append(statement + " | " + (new Date()) + "\n");
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return "there was an error accessing the database";
	}
}
