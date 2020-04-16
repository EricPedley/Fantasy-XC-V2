package main.java.testing;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import main.java.DatabaseConnector;

public class DBSetup {
	public static void main(String[] args) {
		// use execute(String query) method for creating or deleting tables
		String statement = "CREATE TABLE users2 (userID SERIAL PRIMARY KEY, username TEXT, password TEXT);";
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
}
