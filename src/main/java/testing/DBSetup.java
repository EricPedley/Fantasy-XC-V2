package main.java.testing;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import main.java.DatabaseConnector;

public class DBSetup {
	public static void main(String[] args) {
		// use execute(String query) method for creating or deleting tables
		String statement = "CREATE TABLE rosters (userID INTEGER PRIMARY KEY, r1 INTEGER, r2 INTEGER, r3 INTEGER, r4 INTEGER, r5 INTEGER, r6 INTEGER, r7 INTEGER);";
		FileWriter f;
		try {
			DatabaseConnector c = new DatabaseConnector();
			c.executeUpdate(statement);
			f = new FileWriter("dblogs.txt",true);
			f.append(statement + " | " + (new Date()) + "\n");
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
