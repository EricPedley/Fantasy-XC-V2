package main.java.endpoints;

import org.json.JSONObject;

public class Rosters implements Endpoint {

	public String handleGET(JSONObject params) {
		String id = (String)params.get("id");
		return "request receivedin Rosters endpoint, id in request was: "+id;
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