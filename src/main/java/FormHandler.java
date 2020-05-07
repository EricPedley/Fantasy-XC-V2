package main.java;

import org.json.JSONArray;
import org.json.JSONObject;

public class FormHandler {
	public String handleForm(JSONObject data) {// type 0 is login form, type 1 is signup form
		int type = ((JSONArray) data.get("type")).getInt(0);
		System.out.println("type: " + type);
		if (type == 0) {// form is login
			// first user: (user: joe, pass: who)
			String query = String.format("SELECT userID,password FROM users WHERE username='%s' AND password='%s'",
					((JSONArray) data.get("user")).getString(0), ((JSONArray) data.get("pass")).getString(0));
			String result = DatabaseConnector.executeQuery(query).get(0);
			System.out.println("result of query: "+result);
			if (result.equals("results was empty")) {
				return "sorry, could not find this combo of username and password";
			}
			return result;

		} else if (type == 1) {// form is signup
			String statement = String.format("INSERT INTO users VALUES(DEFAULT,'%s','%s');",
					((JSONArray) data.get("user")).getString(0), ((JSONArray) data.get("pass")).getString(0));
			DatabaseConnector.executeUpdate(statement);
			return "signup attempted(may or may not have been completed";
		} else {
			return "unsupported form type, browser error";
		}
	}
}
