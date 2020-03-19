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
	public void run() {// each time this method runs it handles one request
		BufferedReader in = null;// input stream
		BufferedOutputStream dataOut = null;// output stream for body of response
		PrintWriter headerOut = null;// output stream for head of response
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String rawRequest = in.readLine();// first line of request
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
				String contentType = getContentType(resource);// content type is what type of file
				byte[] outputData = readFileData(file);// converts file into array of bytes
				dataOut = new BufferedOutputStream(client.getOutputStream());
				headerOut = new PrintWriter(client.getOutputStream());
				headerOut.print("HTTP/1.1 200 OK\r\n");// you need to use \r\n instead of a println or else it crashes
				headerOut.print("Server: Fantasy XC Server : 1.0\r\n");
				headerOut.print("Date: " + new Date() + "\r\n");
				headerOut.print("Content-type: " + contentType + "\r\n");
				headerOut.print("Content-length: " + file.length() + "\r\n");
				headerOut.print("\r\n"); // blank line between headers and content, very important !
				headerOut.flush(); // flush character output stream buffer
				dataOut.write(outputData, 0, outputData.length);
				dataOut.flush();

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				if (headerOut != null) {// this condition happens when the response is null and the code to initialize
										// the output streams never runs
					dataOut.close();
					headerOut.close();
				}
				client.close();
			} catch (IOException e) {
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