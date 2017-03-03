package odev.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import odev.util.ProcessUtils;

public class ConnectClearSettle {
	private URL url;
	private HttpURLConnection connection;
	private OutputStreamWriter outWriter;
	
	public JSONObject getDataFromURL(String url,JSONObject params,String auth,String type){
		try {
			this.url = new URL(url);
			this.connection = (HttpURLConnection) this.url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestMethod(type);
			if(!(auth == null || auth.equals("")))
				connection.setRequestProperty ("Authorization",auth);
			outWriter = new OutputStreamWriter(connection.getOutputStream());
			outWriter.write(params!=null ? params.toString() : "");
			outWriter.flush();
			StringBuilder sb = new StringBuilder();  
			if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
			    BufferedReader br = new BufferedReader(
			            new InputStreamReader(connection.getInputStream(), "utf-8"));
			    String line = null;  
			    while ((line = br.readLine()) != null) {  
			        sb.append(line + "\n");  
			    }
			    br.close();
			    return new JSONObject(sb.toString());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return ProcessUtils.returnError(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			return ProcessUtils.returnError(e.getMessage());
		} catch (JSONException e) {
			e.printStackTrace();
			return ProcessUtils.returnError(e.getMessage());
		}

		return null;
	}
	
}
