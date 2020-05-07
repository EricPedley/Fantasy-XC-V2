package main.java.testing;

import main.java.DatabaseConnector;

public class DBSetup {
	public static void main(String[] args) {
		// use execute(String query) method for creating or deleting tables
		String statement = "INSERT INTO rosters VALUES(4,1,2,3,4,5,6,7);";
			DatabaseConnector.executeUpdate(statement);
	}
}
