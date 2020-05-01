package main.java.endpoints;

import org.json.JSONObject;

public class Rosters implements Endpoint {

	public String handleGET(JSONObject params) {
		System.out.println(params);
		return "this is the response for rosters get, not fully implemented yet";
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
