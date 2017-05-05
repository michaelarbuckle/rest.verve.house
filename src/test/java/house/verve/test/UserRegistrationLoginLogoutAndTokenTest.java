package house.verve.test;
import java.net.URL;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;



import org.json.JSONObject;

import house.verve.model.User;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.Locale;
import java.util.ResourceBundle;
public class UserRegistrationLoginLogoutAndTokenTest {

	static HashMap errors = new HashMap();
	static		{
		errors.put( 401 ,"Unauthorized: Access is denied due to invalid credentials");
		errors.put( 400 ,"Bad Request");
		errors.put( 403 ,"Forbidden");
		errors.put( 404 ,"Not Found");
		errors.put( 405 ,"Method Not Allowed");
		errors.put( 406 ,"Not Acceptable");
		errors.put( 407 ,"Proxy Authentication Required");
		errors.put( 408 ,"Request Timeout");
		errors.put( 500 , "500 Internal server error");
			}
	
	public static void main(String[] args)
	{
		UserRegistrationLoginLogoutAndTokenTest  ut = new UserRegistrationLoginLogoutAndTokenTest ();
		UUID u =UUID.randomUUID();
		System.out.println(u.toString());
		String username=u.toString()+"@mail.zoo";
		String pwd = "pwd";
		
	 	ut.registerUser(username, pwd, username);
	//	ut.showUsersTest();
	//	ut.showMessagesTest();
		ut.loginPostTest(username,pwd );

		ut.showSensorsTest();
	
		//ut.registerJSONTest();
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
	void registerUser(String username, String password, String email)
	{
		try {
		URL url = new URL(baseUrl+"/registerNewUser");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Content-Type", "application/json");

		conn.setRequestProperty("datetime", getDateTimeString());
	 //   conn.setRequestProperty("authorization", getAuthorization());
	 //  conn.setUseCaches(false);
	    conn.setDoInput(true); // Triggers POST
	    conn.setDoOutput(true);     
	    JSONObject user=new JSONObject();
		user.put("username", username);
		user.put("password", password);
		user.put("firstName",  username);
		user.put("lastName", username);
		user.put("email", email);
	    
		System.out.println("json"+user.toString());
		OutputStreamWriter wr= new OutputStreamWriter(conn.getOutputStream());
		wr.write(user.toString());
		wr.flush();
		displayResponse(conn);
		
		  } catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (Exception e) {

				e.printStackTrace();

			  }
	}
	void loginPostTest(String username,String password)
	{
		HttpURLConnection conn=null;
		try {
		//check token in response
		URL url = new URL(baseUrl+"/login");
		System.out.println("URL "+url);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Content-Type", "application/json");

	    conn.setDoInput(true); // Triggers POST
	    conn.setDoOutput(true);     
	
	    JSONObject user=new JSONObject();
		user.put("password", password);
		user.put("username", username);
		
		
		ObjectMapper mapper = new ObjectMapper();
		  mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

	 	//JSON from file to Object
		System.out.println("mapper toJSON"+  mapper.writeValueAsString(new User("userabc","pwwd")));
		  
		
		//JSON from String to Object
		User obj = mapper.readValue(user.toString(), User.class);
		System.out.println("mapped "+obj);
		
		 
		System.out.println("json"+user.toString());
		OutputStreamWriter wr= new OutputStreamWriter(conn.getOutputStream());
		wr.write(user.toString());
		wr.flush();

		displayResponse(conn);
		for (String k :conn.getHeaderFields().keySet())
		{
			String v = conn.getHeaderField(k);
			System.out.println("key="+k+" value="+v);
		}
	
	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  } finally
		{
		  if (conn!=null)
			conn.disconnect();

		}

	}
	void loginGetTest(String username,String password)
	{
		try {	//check token in response
		//URL url = new URL(baseUrl+"/login -u"+username+"-p"+password);
		URL url = new URL(baseUrl+"/login?username="+username+"&password="+password);
			HttpURLConnection conn;
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			//conn.setRequestProperty("Authorization", "OAuth " + getAuthorization());

			displayResponse(conn);
		//	conn.getHeaderField(")
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void useToken() 
	{
		try {
		URL url = new URL(baseUrl+"/users");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.addRequestProperty("client_id", username);
		conn.addRequestProperty("client_secret", username);
		conn.setRequestProperty("Authorization", "OAuth " + lastToken);
		displayResponse(conn);
		}catch(MalformedURLException e){e.printStackTrace();} 
		catch(IOException e){e.printStackTrace();}finally{}
		 
		
	}
	void LogoutTest()
	{
		
		
	}
	
	void registerJSONTest()
	{
		try {
//		String url="http://rest.verve.house/registerNewUser";
		String url=baseUrl+"/registerNewUser";
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

		displayResponse(conn);

	 
		}catch(MalformedURLException e){e.printStackTrace();} 
		catch(IOException e){e.printStackTrace();}finally{}
		 
	}
	
	void showUsersTest()
	{
		try {
 		String url=baseUrl+"/users";
		URL object=new URL(url);

		HttpURLConnection conn = (HttpURLConnection) object.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestMethod("GET");
		 

		displayResponse(conn);

	 
		}catch(MalformedURLException e){e.printStackTrace();} 
		catch(IOException e){e.printStackTrace();}finally{}
		 
	}
	void showMessagesTest()
	{
		try {
 		String url=baseUrl+"/messages";
		URL object=new URL(url);

		HttpURLConnection conn = (HttpURLConnection) object.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestMethod("GET");
		 

		displayResponse(conn);

	 
		}catch(MalformedURLException e){e.printStackTrace();} 
		catch(IOException e){e.printStackTrace();}finally{}
		 
	}
	
	void showSensorsTest()
	{
		try {
 		String url=baseUrl+"/sensors";
		URL object=new URL(url);

		HttpURLConnection conn = (HttpURLConnection) object.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestMethod("GET");
		 

		displayResponse(conn);
		for (String k :conn.getHeaderFields().keySet())
		{
			String v = conn.getHeaderField(k);
			System.out.println("key="+k+" value="+v);
		}

	 
		}catch(MalformedURLException e){e.printStackTrace();} 
		catch(IOException e){e.printStackTrace();}finally{}
		 
	}
	
	
	void displayResponse(HttpURLConnection conn)
	{
	try{	
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
		    System.out.println("HTTP error code : "+conn.getResponseCode());  	
		    String message =conn.getResponseMessage();
		    if (message == null || message.length() == 0)
		    	message = (String)errors.get(conn.getResponseCode());
		    System.out.println(message);  
		} 
	}catch(Exception e){e.printStackTrace();}
	}
}
