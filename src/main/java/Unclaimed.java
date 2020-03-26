package main.java;

import main.java.endpoints.Endpoint;

public class Unclaimed {
	public static void main(String[] args) {
		try {
			Class c = Class.forName("main.java.endpoints.Waivers");
			Endpoint e = (Endpoint) c.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
