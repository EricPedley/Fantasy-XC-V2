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
			int port = System.getenv("PORT");
			System.out.println("system port is:"+port);
			ServerSocket socket = new ServerSocket(port);
			while(true) {//each iteration of this loop handles a different request
				System.out.println("ready to accept a request");
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
    	client=s;
    }

	@Override
	public void run() {
		try {
			File index = new File("public/index.html");
			byte[] outputData = readFileData(index);
			BufferedOutputStream outputStream = new BufferedOutputStream(client.getOutputStream());
			PrintWriter out = new PrintWriter(client.getOutputStream());
			out.println("HTTP/1.1 200 OK");
			out.println("Server: Java HTTP Server from SSaurel : 1.0");
			out.println("Date: " + new Date());
			out.println("Content-type: " + "text/html");
			out.println("Content-length: " + index.length());
			out.println(); // blank line between headers and content, very important !
			out.flush(); // flush character output stream buffer
			outputStream.write(outputData);
			outputStream.flush();
		} catch (IOException e) {				
			e.printStackTrace();
		}

		
	}
	/*
	 * Converts a file to an array of bytes
	 */
	private byte[] readFileData(File file) throws FileNotFoundException, IOException {
		int length = (int)file.length();
		byte[] fileData = new byte[length];
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(fileData);
		} finally {
			if(fileInputStream!=null)
				fileInputStream.close();
				
		}
		return fileData;
	}
}