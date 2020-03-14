package main.java;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class Server implements Runnable {

	Socket client;

	public Server(Socket s) {
		client = s;
	}

	@Override
	public void run() {//each time this method runs it handles one request
		BufferedReader in = null;
		BufferedOutputStream outputStream = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String rawRequest = in.readLine();
			if (rawRequest == null) {
				System.out.println("null request, terminating thread");
				return;
			}
			String[] request = rawRequest.split(" ");
			String method = request[0];
			String resource = request[1];

			if (method.equals("GET")) {
				if (resource.endsWith("/")) {
					resource += "index.html";
				}
				File file = new File("public/" + resource);
				String contentType = getContentType(resource);
				byte[] outputData = readFileData(file);
				outputStream = new BufferedOutputStream(client.getOutputStream());
				out = new PrintWriter(client.getOutputStream());
				out.print("HTTP/1.1 200 OK\r\n");// you need to use \r\n instead of a println or else it crashes
				out.print("Server: Fantasy XC Server : 1.0\r\n");
				out.print("Date: " + new Date() + "\r\n");
				out.print("Content-type: " + contentType + "\r\n");
				out.print("Content-length: " + file.length() + "\r\n");
				out.print("\r\n"); // blank line between headers and content, very important !
				out.flush(); // flush character output stream buffer
				outputStream.write(outputData, 0, outputData.length);
				outputStream.flush();

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				if(out!=null) {
					outputStream.close();
					out.close();
				}
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

	private String getContentType(String fileRequested) {
		if (fileRequested.endsWith(".htm") || fileRequested.endsWith(".html"))
			return "text/html";
		else
			return "text/plain";
	}

}