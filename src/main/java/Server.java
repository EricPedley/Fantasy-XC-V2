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

import org.json.JSONObject;

public class Server implements Runnable {

	Socket client;

	public Server(Socket s) {
		client = s;
	}

	public void run() {// each time this method runs it handles one request
		BufferedReader in = null;// input stream
		BufferedOutputStream dataOut = null;// output stream for body of response
		PrintWriter headerOut = null;// output stream for head of response
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String firstLine = in.readLine();// first line of request
			if (firstLine == null) {
				System.out.println("null request, terminating thread");
				return;
			}
			System.out.println("request first line: "+firstLine);
			String[] request = firstLine.split(" ");
			String method = request[0];
			String resource = request[1];
			if (!(resource.length() == 1 || resource.contains("."))) {// this means that it's a request to an endpoint.
																		// ex: http://fantasyxc.com/Rosters
				String[] bodyAndContentType = readBody(in);
				String response = new EndpointHandler().handle(method, resource.substring(1),
						parseBody(bodyAndContentType[0], bodyAndContentType[1]));
				sendResponse(dataOut, headerOut, response);

			} else if (method.equals("GET")) {
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
			} else if (method.equals("POST")) {
				System.out.println("post request first line:" + firstLine);

				String[] bodyAndContentType = readBody(in);
				String contentType = bodyAndContentType[0];
				String body = bodyAndContentType[1];
				System.out.println("body:" + body);
				JSONObject bodyObj = parseBody(body, contentType);
				String response = "data was not form type(line 71 Server.java)";
				System.out.println("content-type:" + contentType);
				if (contentType.equals("application/x-www-form-urlencoded")) {// if the data is from a form
					response = new FormHandler().handleForm(bodyObj);
				}
				sendResponse(dataOut, headerOut, response);// this handles writing all the headers and writing response
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
		else if(fileRequested.endsWith(".css"))
			return "text/css";
		else
			return "text/plain";
	}

	private void sendResponse(BufferedOutputStream dataOut, PrintWriter headerOut, String response) throws IOException {
		byte[] outputData = response.getBytes();
		dataOut = new BufferedOutputStream(client.getOutputStream());
		headerOut = new PrintWriter(client.getOutputStream());
		headerOut.print("HTTP/1.1 200 OK\r\n");// you need to use \r\n instead of a println or else it crashes
		headerOut.print("Server: Fantasy XC Server : 1.0\r\n");
		headerOut.print("Date: " + new Date() + "\r\n");
		headerOut.print("Content-type: " + "text" + "\r\n");
		headerOut.print("Content-length: " + response.length() + "\r\n");
		headerOut.print("Connection: close\r\n"); // Will close stream
		headerOut.print("\r\n"); // blank line between headers and content, very important !
		headerOut.flush(); // flush character output stream buffer
		dataOut.write(outputData, 0, outputData.length);
		dataOut.flush();
	}

	private JSONObject parseBody(String body, String contentType) {
		if (contentType.equals("application/x-www-form-urlencoded")) {
			JSONObject obj = new JSONObject();
			String[] pairs = body.split("&");
			for (String pair : pairs) {
				int separator = pair.indexOf('=');
				System.out.println("(server ln 144)pair: " + pair);
				System.out.println(pair.substring(0, separator) + "|" + pair.substring(separator + 1));
				obj.append(pair.substring(0, separator), pair.substring(separator + 1));
			}
			return obj;
		} else if (contentType.equals("application/json")) {
			return new JSONObject(body);
		}
		return null;
	}

	private String[] readBody(BufferedReader in) throws NumberFormatException, IOException {
		String lastLine = null;
		int contentLength = -1;
		String contentType = null;
		while ((lastLine = in.readLine()) != null) {// reads headers
			if (lastLine.length() > 9) {
				if (contentLength == -1 && lastLine.substring(0, 9).equals("Content-L"))
					contentLength = Integer.parseInt(lastLine.substring(16));
				if (contentType == null && lastLine.substring(0, 9).equals("Content-T"))
					contentType = lastLine.substring(14);
			}
			if (lastLine.length() == 0)
				break;

		}
		String body = "";
		for (int i = 0; i < contentLength; i++) {
			System.out.println("reading characters");
			body += (char) in.read();
		}
		String[] result = { contentType, body };
		return result;
	}

}