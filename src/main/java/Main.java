package main.java;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
	public static void main(String[] args) {
		
		
		try {
			String port = System.getenv("PORT");
			String staticPath = System.getenv("PATH_TO_STATIC");
			if (port == null) {
				port = "6969";
			}
			if(staticPath==null) {
				staticPath = "src/main/webapp/";
			}
			ServerSocket socket = new ServerSocket(Integer.parseInt(port));
			System.out.println("Listening on port "+port);
			while (true) {// each iteration of this loop handles a different request
				Server s = new Server(socket.accept(),staticPath);
				Thread t = new Thread(s);
				t.run();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
//		catch(SQLException e2) {
//			e2.printStackTrace();
//		} catch(ClassNotFoundException e3) {
//			e3.printStackTrace();
//		}
	}
}

