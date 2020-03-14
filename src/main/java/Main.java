package main.java;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
	public static void main(String[] args) {
		try {
			String port = System.getenv("PORT");
			if (port == null) {
				port = "6969";
			}
			ServerSocket socket = new ServerSocket(Integer.parseInt(port));
			while (true) {// each iteration of this loop handles a different request
				Server s = new Server(socket.accept());
				Thread t = new Thread(s);
				t.run();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
