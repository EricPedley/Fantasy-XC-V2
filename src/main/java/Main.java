package main.java;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
	public static void main(String[] args) {
		String databaseURL = "jdbc:postgresql://essggmgpyritgn:226d5952c81905ab5d6e176ac53e686d7fa41d25aece4f631cdebe78855a6dc8@ec2-52-23-14-156.compute-1.amazonaws.com:5432/dbo4aa6j9pt62c";
		new DatabaseConnector().connect(databaseURL);
		try {
			String port = System.getenv("PORT");
			if (port == null) {
				port = "6969";
			}
			ServerSocket socket = new ServerSocket(Integer.parseInt(port));
			System.out.println("Listening on port "+port);
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

mvn deploy:deploy-file -Durl=file://C:\Users\Generic\git\Fantasy-XC-V2\repo -Dfile=postgresql-42.2.11.jar -DgroupId=org.postgresql -DartifactId=Driver -Dpackaging=jar -Dversion=1.0