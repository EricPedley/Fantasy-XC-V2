package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AthleteIDScraper {
	public static void main(String[] args) {
		scrapeIDs();
	}

	public static void scrapeIDs() {

		String host = "https://www.athletic.net/CrossCountry/School.aspx?SchoolID=1096";
		URL url;
		try {
			url = new URL(host);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.addRequestProperty("User-Agent", "Mozilla/4.0");
			con.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine+"\n");
			}
			in.close();
			con.disconnect();
			String html = content.toString();
			int sI = html.indexOf("\"athletes\":");// short for "start index"
			int eI = html.indexOf("\"coaches\":");// short for "end index"
			String jsonString = "{\n";
			for (int i = html.indexOf("\"ID\"", sI); i != -1 && i < eI; i = html.indexOf("\"ID\":", i + 1)) {
				String id = html.substring(i + 5, i + 13);
				String name = html.substring(i + 22, html.indexOf(" ", i + 22));
				jsonString += "\"" + id + "\":" + "\"" + name + "\",\n";
			}
			jsonString = jsonString.substring(0, jsonString.length() - 2) + "\n}";
			System.out.println(jsonString);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
