package main.java.endpoints;

import java.util.ArrayList;

import org.json.JSONObject;

import main.java.DatabaseConnector;

public class Runner implements Endpoint {

	public String handleGET(JSONObject params) {
		System.out.println("handling get for one runner");
		String statement = "SELECT name FROM athleteIDs WHERE athleteid = "+params.getString("id");
		ArrayList<ArrayList<String>> results = DatabaseConnector.executeQuery(statement);
		if(results==null||results.size()<1)
			return "no runner found";
		return results.get(0).get(0);
	}

	public String handlePOST(JSONObject data) {
		// TODO Auto-generated method stub
		return null;
	}

	public void handlePATCH(JSONObject data) {
		// TODO Auto-generated method stub
		
	}
}
