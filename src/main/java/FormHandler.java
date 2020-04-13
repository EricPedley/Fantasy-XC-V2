package main.java;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

public class FormHandler {
	public void handleForm(JSONObject data) {//type 0 is login form, type 1 is signup form
		int type = ((JSONArray)data.get("type")).getInt(0);
		System.out.println("type: "+type);
		if(type==0) {//form is login
			System.out.println("form is login, not supported yet");
		} else if(type == 1) {//form is signup
			String statement = "INSERT INTO users VALUES(\""+((JSONArray)data.get("user")).getString(0)+"\",\""+((JSONArray)data.get("pass")).getString(0)+"\");";
			System.out.println("statement: "+statement);
			DatabaseConnector db = new DatabaseConnector();
			try {
				db.executeUpdate(statement);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
