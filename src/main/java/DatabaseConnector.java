package main.java;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseConnector {
	public static void executeUpdate(String statement) {
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
				FileWriter f = new FileWriter("dblogs.txt", true);// this only works for local testing
				String loggingOutput = statement + " | " + (new Date()) + "\n";
				System.out.println(loggingOutput);
				f.append(loggingOutput);
				f.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(
					"finished database operation(might not have been successful as this println is in the finally clause");
		}

	}

	public static ArrayList<String> executeQuery(String statement) {
		Connection c = null;
		Statement s = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://ec2-52-23-14-156.compute-1.amazonaws.com:5432/dbo4aa6j9pt62c";
			c = DriverManager.getConnection(url, "essggmgpyritgn",
					"226d5952c81905ab5d6e176ac53e686d7fa41d25aece4f631cdebe78855a6dc8");
			c.setAutoCommit(false);
			s = c.createStatement();
			ResultSet resultsSet = s.executeQuery(statement);
			int numCols = 0;
			int i =0;
			String start = statement.substring(0,statement.indexOf("FROM"));
			while(i!=-1) {
				i=start.indexOf(",",i+1);
				numCols++;
			}
			ArrayList<String> result = new ArrayList<String>();
			
			if (resultsSet.next()) {
				for(int col=0;col<numCols;col++) {
					result.add(resultsSet.getString(col + 1));
				}
			}
			resultsSet.close();
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
				f = new FileWriter("dblogs.txt", true);
				f.append(statement + " | " + (new Date()) + "\n");
				f.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
