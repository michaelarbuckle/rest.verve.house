package house.verve.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import house.verve.model.Timeseries;

public class TimeseriesTest {

	static public Double getRandom()
	{
		
		return Math.random();
	}
	
	Double[] generateTimeSeries(Double min, Double max, int count)
	{
		Double [] ts ={};
		
		return ts;
	}
	Double[] generateARMATimeSeries(Double min, Double max, int count)
	{
		
		Double [] ts ={};
		
		return ts;
	}
	
	void postTimeSeries()
	{
		Double[] values = generateTimeSeries(8.0,12.0,60);
		for (Double v:values)
		{
			
			postSensor(v.toString(),"C","temperature","");
		}
		
	}
	void postSensor(String value, String units,String type,String id)
	{
		try {
//			String url="http://rest.verve.house/registerNewUser";
			String url=baseUrl+"/sensors";
			URL object=new URL(url);

			HttpURLConnection conn = (HttpURLConnection) object.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestMethod("POST");
			
			JSONObject cred = new JSONObject();
			JSONObject auth=new JSONObject();
			JSONObject user=new JSONObject();
			user.put("username","adm");
			user.put("password", "pwd");
			user.put("firstName", "adm");
			user.put("lastName", "adm");
			user.put("email", "email");

			OutputStreamWriter wr= new OutputStreamWriter(conn.getOutputStream());
			wr.write(user.toString());
			
			StringBuilder sb = new StringBuilder();  
			int HttpResult = conn.getResponseCode(); 
			if (HttpResult == HttpURLConnection.HTTP_OK) {
			    BufferedReader br = new BufferedReader(
			            new InputStreamReader(conn.getInputStream(), "utf-8"));
			    String line = null;  
			    while ((line = br.readLine()) != null) {  
			        sb.append(line + "\n");  
			    }
			    br.close();
			    System.out.println("" + sb.toString());  
			} else {
			    System.out.println(conn.getResponseMessage());  
			} 
			
			}catch(MalformedURLException e){e.printStackTrace();} 
			catch(IOException e){e.printStackTrace();}finally{}
			 
		
	}
	
	static String lastToken="";
	static String secret="";		
	static String username="";		
//	static String baseUrl="http://rest.verve.house";
	static String baseUrl="http://localhost:8080";

	String getAuthorization()
	{
		return lastToken;
	}
	String getDateTimeString()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDD-HH:mm.ss"); 
		Date d = new Date();
		return sdf.format(d);
	}
}
