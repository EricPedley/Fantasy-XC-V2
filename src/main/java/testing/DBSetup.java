package main.java.testing;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import main.java.DatabaseConnector;
public class DBSetup {
	public static void main(String[] args) {
		DatabaseConnector c = new DatabaseConnector();
		try {
			String query = "CREATE TABLE users (userID SERIAL PRIMARY KEY, username TEXT, password TEXT);";
			c.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
