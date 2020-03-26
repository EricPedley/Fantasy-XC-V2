package main.java.endpoints;

import org.json.JSONObject;

public interface Endpoint {
	public abstract void handeGET();
	public abstract void handePOST(JSONObject data);
	public abstract void handePATCH(JSONObject data);
}
