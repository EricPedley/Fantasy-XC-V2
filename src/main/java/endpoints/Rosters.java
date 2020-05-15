package main.java.endpoints;

import java.util.ArrayList;

import org.json.JSONObject;

import main.java.DatabaseConnector;

public class Rosters implements Endpoint {

	public String handleGET(JSONObject params) {
		String id = (String)params.get("id");
		String statement = String.format("SELECT r1,r2,r3,r4,r5,r6,r7 FROM rosters where userID = %s",id);
		ArrayList<String> data = DatabaseConnector.executeQuery(statement);
		String rosterNums = "";
		for(String s: data) {
			rosterNums+=s+",";
		}
		if(rosterNums.length()<1)
			return "query returned nothing for athlete ids";
		rosterNums = "{"+rosterNums.substring(0,rosterNums.length()-1)+"}";
		return "athlete IDs: "+rosterNums;
		// TODO Auto-generated method stub
	}
	
	public String handlePOST(JSONObject data) {
		// TODO Auto-generated method stub
		return null;
	}

	public void handlePATCH(JSONObject data) {
		// TODO Auto-generated method stub
		
	}
	
}
