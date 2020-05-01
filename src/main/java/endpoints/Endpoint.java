package main.java.endpoints;

import org.json.JSONObject;

public interface Endpoint {
	public abstract String handleGET(JSONObject params);
	public abstract String handlePOST(JSONObject data);
	public abstract void handlePATCH(JSONObject data);
}
