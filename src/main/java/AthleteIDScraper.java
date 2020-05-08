package main.java;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class AthleteIDScraper {
	public static void main(String[] args) {
		scrapeIDs();
	}

	public static void scrapeIDs() {

		String host = "https://www.athletic.net/CrossCountry";
		Socket socket;
		
		try {
			socket = new Socket(host, 80);
			String request = "GET /School.aspx?SchoolID=1096 HTTP/1.0\r\n\r\n";
			OutputStream os = socket.getOutputStream();
			os.write(request.getBytes());
			os.flush();

			InputStream is = socket.getInputStream();
			int ch;
			while( (ch=is.read())!= -1)
			    System.out.print((char)ch);
			socket.close();  
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  

	}
}
