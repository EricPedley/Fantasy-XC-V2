package main.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.json.JSONObject;

import main.java.endpoints.Endpoint;

public class EndpointHandler {
	public EndpointHandler() {
		
	}
	
	public String handle(String method, String endpoint, JSONObject request) {
		try {
			int separator = endpoint.indexOf("?");
			//if there isn't a question mark it uses endpoint, if there is one it uses the part of endpoint before the ?
			Class cl = Class.forName("main.java.endpoints."+((separator==-1)?endpoint:endpoint.substring(0,separator)));
			Constructor co = cl.getConstructor();
			Endpoint e = (Endpoint)co.newInstance();
			if(method.equals("POST")) {
				return e.handlePOST(request);
			} else if(method.equals("GET")) {
				if(separator!=-1) {
					String[] paramPairs = endpoint.substring((separator+1)).split("&");
					JSONObject params = new JSONObject();
					for(String pair: paramPairs) {
						String[] keyVal = pair.split("=");//example: "foo=bar" would be {foo,bar}
						params.put(keyVal[0], keyVal[1]);
					}
					return e.handleGET(params);
				} else {
					return e.handleGET(null);
				}
			} else
				return "method for endpoint request was not post, not implemented on server yet";
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			return "endpoint not found";
		} catch (NoSuchMethodException e1) {//I think this is for when the constructor isn't defined
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {//I honestly don't know when this would fire
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "unexpected server behavior in method 'handle' of EndpointHandler.java";
	}
}
