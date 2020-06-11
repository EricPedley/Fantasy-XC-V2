package main.java.testing;

import main.java.DatabaseConnector;

public class DBSetup {
	public static void main(String[] args) {
		// use execute(String query) method for creating or deleting tables
		//String statement = "INSERT INTO athleteIDs VALUES(333333,\'Rishab\');";
		String statement = "INSERT INTO rosters VALUES(5,123456,6543210,696969,420420,111111,222222,333333);";
		DatabaseConnector.executeUpdate(statement);
		System.out.println(DatabaseConnector.executeQuery(statement));
	}
}
