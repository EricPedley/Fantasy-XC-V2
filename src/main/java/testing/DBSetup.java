package main.java.testing;

import main.java.DatabaseConnector;

public class DBSetup {
	public static void main(String[] args) {
		// use execute(String query) method for creating or deleting tables
		String statement = "INSERT INTO athleteids (athleteID,name) VALUES(420420,'Sid');";
		DatabaseConnector.executeUpdate(statement);
		//System.out.println(DatabaseConnector.executeQuery(statement));
	}
}
