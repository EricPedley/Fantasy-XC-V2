package main.java;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server implements Runnable {

	Socket client;

	public static void main(String[] args) {
		try {
			String port = System.getenv("PORT");
			if (port == null) {
				port = "6969";
			}
			System.out.println("system port is:" + port);
			ServerSocket socket = new ServerSocket(Integer.parseInt(port));
			while (true) {// each iteration of this loop handles a different request
				System.out.println("ready to accept a request(loop restarted)");
				Server s = new Server(socket.accept());
				Thread t = new Thread(s);
				t.run();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Server(Socket s) {
		client = s;
	}

	@Override
	public void run() {
		BufferedReader in = null;
		BufferedOutputStream outputStream = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			File file = null;
			String rawRequest = in.readLine();
			System.out.println("raw request: " + rawRequest);
			if (rawRequest != null) {
				String[] request = rawRequest.split(" ");
				String method = request[0];
				System.out.println(method);
				String resource = request[1];
				if (method.equals("GET")) {
					if (resource.endsWith("/")) {
						resource += "index.html";
						System.out.println("request is get and ends with a slash");
					}
					file = new File("public/" + resource);

					byte[] outputData = readFileData(file);
					outputStream = new BufferedOutputStream(client.getOutputStream());
					out = new PrintWriter(client.getOutputStream());
					out.println("HTTP/1.1 200 OK");
					out.println("Server: Eric's First Java Server : 1.0");
					out.println("Date: " + new Date());
					out.println("Content-type: " + "text/html");
					out.println("Content-length: " + file.length());
					out.println(); // blank line between headers and content, very important !
					out.flush(); // flush character output stream buffer
					outputStream.write(outputData, 0, outputData.length);
					outputStream.flush();// socketexception here
					System.out.println("outputstream flushed");

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
//				System.out.println("streams closing");
//				if (outputStream != null) {
//					outputStream.close();// socketexception here
//					in.close();
//					out.close();
//				}
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/*
	 * Converts a file to an array of bytes
	 */
	private byte[] readFileData(File file) throws FileNotFoundException, IOException {
		int length = (int) file.length();
		byte[] fileData = new byte[length];
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(fileData);
		} finally {
			if (fileInputStream != null)
				fileInputStream.close();

		}
		return fileData;
	}
}