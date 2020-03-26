package main.java.testing;
import java.sql.SQLException;

import main.java.DatabaseConnector;
public class DBSetup {
	public static void main(String[] args) {
		DatabaseConnector c = new DatabaseConnector();
		try {
			c.executeUpdate("CREATE TABLE users (athleteID INTEGER);");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
