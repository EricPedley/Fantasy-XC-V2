package main.java.endpoints;

import java.util.ArrayList;

import org.json.JSONObject;

import main.java.DatabaseConnector;

public class AllRunners implements Endpoint {

	public String handleGET(JSONObject params) {
		System.out.println("handling get for allrunners");
		String statement = "SELECT athleteid, name FROM athleteIDs";
		ArrayList<ArrayList<String>> results = DatabaseConnector.executeQuery(statement);
		String response = results.toString();
		return response.substring(2,response.length()-2);
	}

	public String handlePOST(JSONObject data) {
		// TODO Auto-generated method stub
		return null;
	}

	public void handlePATCH(JSONObject data) {
		// TODO Auto-generated method stub
		
	}
}
