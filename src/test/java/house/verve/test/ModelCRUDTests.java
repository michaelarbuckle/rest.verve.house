package house.verve.test;
import java.net.URL;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import house.verve.model.Action;
import house.verve.model.ActionParameter;
import house.verve.model.DeviceData;
import house.verve.model.Chat;
import house.verve.model.Device;
import house.verve.model.Message;
import house.verve.model.Topic;
import house.verve.model.Timeseries;
import house.verve.model.Sensor;
import house.verve.model.SensorData;
import house.verve.model.Structure;
import house.verve.model.Space;
import house.verve.model.SpaceRole;
import house.verve.model.User;
import house.verve.utils.DataUtil; 
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.Locale;
import java.util.ResourceBundle;
public class ModelCRUDTests {
	   private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	static HashMap errors = new HashMap();
	static		{
		errors.put( 200 ,"");
		
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
		ModelCRUDTests  ut = new ModelCRUDTests ();
		UUID u =UUID.randomUUID();
		String timestamp = DataUtil.getDateTimeString();
		System.out.println(u.toString());
		String username=u.toString()+"@mail.zoo";
		String pwd = "pwd";
		
	 	ut.registerUser(username, pwd, username);
	 	ut.loginPostTest(username, pwd);
	 	//ut.structurePostTest(u,u,u,u,getDateTimeString());
	 	String[] types = {"CO2","VOC","radon","temperature","pressure","dust","humidity","EMF"};
	 	String[] units = {"ppm","ppm","Bq","C","psi","mg/m^3","%","V/m^2"};
		
	 	 /*
		 for (String ss:types)
		 {
		 		 
		 Sensor s = new Sensor();
		 
		 s.setName(ss);
		 s.setModel(ss);
		 s.setTimestamp( getDateTimeString());
		 s.setuUID(u.toString()+ss );
		 s.setValue(getRandomValue(ss).toString());
		 s.setType(ss);
		 s.setUnits("ppm");
		
		 Date start = new Date();
		 List<Date> dayTimestmps = DataUtil.getDateSeries(start, 300, 12);
		 List<Date> emptyList = new ArrayList<Date>();	
		 s.setDayTimeSeriesTimestamps(emptyList);
		 List<Double> dayTimeSeries = new ArrayList<Double>();		 
		 s.setDayTimeSeries(dayTimeSeries);
		 for (Date dt:dayTimestmps)
		 {
	// 	 String timestamp = DataUtil.getDateTimeString(dt);
		Double v=	 getRandomValue(ss); 
		 s.addDayTimeSeries(v, dt);
		 }
		 
		 s.setDayMin(getRandomValue(ss) );
		 s.setDayMax(getRandomValue(ss));
		 s.setDayAvg(getRandomValue(ss));
		 List<Date> weekTimestmps = DataUtil.getDateSeries(start, 3*3600, 56);
		 s.setWeekTimeSeriesTimestamps(emptyList);
		 List<Double> weekTimeSeries = new ArrayList<Double>();		 
		 s.setWeekTimeSeries(dayTimeSeries);
		 for (Date dt:weekTimestmps)
		 {
	// 	 String timestamp = DataUtil.getDateTimeString(dt);
		Double v=	 getRandomValue(ss); 
		 s.addWeekTimeSeries(v, dt);
		 }
		 
		 s.setWeekMin(getRandomValue(ss) );
		 s.setWeekMax(getRandomValue(ss));
		 s.setWeekAvg(getRandomValue(ss));
		 
		 
		 String sJson = ut.getJSON(s);
		 ut.crud(sJson,"sensors",u.toString());
		 System.out.println(sJson);	
		// break;
	}
		 */
		
				
		 DeviceData dd = new DeviceData() ;		 
		 int i=0;
		 for (String ss:types)
		 {
		 SensorData sd = new SensorData() ;
		 sd.setType(ss);
		 sd.setValue(ut.getRandomValue(ss).toString());
		 sd.setUnits( units[i++]);
		 sd.setUuid(u+"-"+ss);
		 dd.addSensor(sd);
		 }		 
	//	 dd.setId(u.toString());
		 dd.setTimestamp("20170423 17:30.14");
		 dd.setUuid(u.toString());
		 dd.setName(username);
		 dd.setTenantId(u.toString());		 
		 String ddataJson = ut.getJSON(dd);
		 ut.crud(ddataJson,"deviceData",u.toString());
		 		 
		 System.out.println(ddataJson);
		 
		 Timeseries ts = new Timeseries(u.toString(), timestamp);
		 
		 ut.get("timeseries", "", u.toString()+"-"+"CO2");
		// ut.getAll("timeseries", "", u.toString()+"-"+"CO2");
		 
		 
		 if (true) return;
		 
		 
		 
		 
		 
		 Device d = new Device();
		 String dJson = ut.getJSON(d);
		 ut.crud(ddataJson,"devices",u.toString());
 	
		 Action a = new Action();
		 ActionParameter ap1 = new ActionParameter();
		 ActionParameter ap2 = new ActionParameter();
		 a.addActionParameter(ap1);
		 a.addActionParameter(ap2);
		 
		 String actionJson = ut.getJSON(a);
		 ut.crud("actions",actionJson,u.toString());
	
		 System.out.println(actionJson); 
		 
		 Structure st = new Structure();
		 String stJson = ut.getJSON(st);
		 ut.crud(stJson,"sensors",u.toString());
		 System.out.println(stJson);
		 Space sp = new Space(); ;
		 String spJson = ut.getJSON(sp);
		 ut.crud(spJson,"sensors",u.toString());
		 System.out.println(spJson);
		 SpaceRole sr = new SpaceRole();
		 String srJson = ut.getJSON(sr);
		 ut.crud(srJson,"sensors",u.toString());
		 System.out.println(srJson);
		 
		 Message m = new Message();
		 Chat cht = new Chat();
		 Topic topic = new Topic();
		
		
		
	}

	
static Double getRandomValue(String type)
	{
		
		if ("CO2".equals(type))
			return DataUtil.getRandomDouble(100.0,700.0);
		else if ("pressure".equals(type))
			return DataUtil.getRandomDouble(100.0,700.0) ;
		else if ("radon".equals(type))
			return DataUtil.getRandomDouble(100.0,700.0) ;
		else						
			return DataUtil.getRandomDouble(0.0,30.0) ;
		
	}
	
	String getJSON(Object object)
	{
	//Convert Java object to JSON, writeValue(...)
		 //Object to JSON in String
		 String jsonInString="";
		try {
	 ObjectMapper mapper = new ObjectMapper();

		jsonInString = mapper.writeValueAsString(object);
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
	 return jsonInString;
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
		OutputStreamWriter wr= new OutputStreamWriter(conn.getOutputStream());
		wr.write(user.toString());
		wr.flush();

		displayResponse(conn);
	 
		String token = conn.getHeaderField("X-AUTH-TOKEN");
		LOGGER.error("token = "+token);
		this.lastToken = token;
		 
	
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
			String token = conn.getHeaderField("X-AUTH-TOKEN");
			LOGGER.error("token = "+token);
			
			//key=X-AUTH-TOKEN value=eyJleHBpcmVzIjoxNDgyODc0OTMyNjE1LCJpZCI6bnVsbCwidXNlcm5hbWUiOiIwZGY5ZjU5MS03NmU0LTQyYmQtOWVjOC1jODcxYWYyYmU2MjhAbWFpbC56b28iLCJlbWFpbCI6bnVsbCwiZmlyc3ROYW1lIjpudWxsLCJsYXN0TmFtZSI6bnVsbCwicGFzc3dvcmQiOiIkMmEkMTAkMGZZMlM4dnQyTjFKY0FFZWpFd0xOLnFhQ0V0aURYWjRiMUdXWFd1aHNaeTZpTU9maXBSUGEiLCJpbWFnZSI6bnVsbCwidGVuYW50SWQiOm51bGwsImVuYWJsZWQiOnRydWUsInNlY3JldCI6IlROTVpGUFlBVkxUQjdEQ1AiLCJ1c2luZzJGQSI6ZmFsc2UsImFjY291bnROb25FeHBpcmVkIjp0cnVlLCJhY2NvdW50Tm9uTG9ja2VkIjp0cnVlLCJjcmVkZW50aWFsc05vbkV4cGlyZWQiOnRydWV9.n/hv58SUww974ECWcsUps4gsESl59cHQxjRiR8AGDX8=

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static String lastToken="";
	static String secret="";		
	static String username="";		
//	static String baseUrl="http://rest.verve.house";
	//static String baseUrl="http://localhost:8080";
	static String baseUrl="http://verve.local:8080";
	

	String getAuthorization()
	{
		return lastToken;
	}
	static String getDateTimeString()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDD HH:mm.ss.SSS"); 
		Date d = new Date();
		return sdf.format(d);
	}
	
	void crud( String json,String path, String oid)
	{ 
		LOGGER.error("crud "+path );
		try {
			
		String oid2 = oid.concat("1");
	//	this.post(path, json, oid);
		
	 	this.put(path, json, oid);
		this.get(path, json, oid);
		
	 	//	this.put(path, json, oid);
		this.getAll(path, json, oid);
	
	//	this.put(path, json, oid);
	//	this.put(path, json, oid);
	
		} catch (Exception e) {

				e.printStackTrace();

			  }
	}
	
	void post(String path, String json, String oid)
	{
		try {//
		URL url = new URL(baseUrl+"/"+path);
		LOGGER.error("post url:"+url );

		//POST
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("datetime", getDateTimeString());
	    conn.setRequestProperty("X-AUTH-TOKEN", getAuthorization());

 	 //  conn.setUseCaches(false);
	    conn.setDoInput(true); // Triggers POST
	    conn.setDoOutput(true);     
	    
		OutputStreamWriter wr= new OutputStreamWriter(conn.getOutputStream());
		wr.write(json);
		wr.flush();
		displayResponse(conn);
		
		  } catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (Exception e) {

				e.printStackTrace();

			  }
	}
	void get(String path, String json, String oid)
	{
		try {
		URL url = new URL(baseUrl+"/"+path+"/"+oid);
		LOGGER.error("get url:"+url );

		//POST
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("datetime", getDateTimeString());
	    conn.setRequestProperty("X-AUTH-TOKEN", getAuthorization());
	 //  conn.setUseCaches(false);
	  //  conn.setDoInput(true); // Triggers POST
	    conn.setDoOutput(true);     
		displayResponse(conn);
		} catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (Exception e) {

				e.printStackTrace();

			  }
	}

	void getAll(String path, String json, String oid)
	{
		try {
		URL url = new URL(baseUrl+"/"+path);
		LOGGER.error("getAll url="+url.toString());
		//POST
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("datetime", getDateTimeString());
	    conn.setRequestProperty("X-AUTH-TOKEN", getAuthorization());
	 //  conn.setUseCaches(false);
	  //  conn.setDoInput(true); // Triggers POST
	    conn.setDoOutput(true);     
		displayResponse(conn);
		} catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (Exception e) {

				e.printStackTrace();

			  }
	}
	
	void put(String path, String json, String oid)
	{
		try {
	

			
			URL url = new URL(baseUrl+"/"+path+"/"+oid);

			LOGGER.error("put url:"+url );

			
		//PUT
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("PUT");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("datetime", getDateTimeString());
	    conn.setRequestProperty("X-AUTH-TOKEN", getAuthorization());
	 //  conn.setUseCaches(false);
	    conn.setDoInput(true); // Triggers POST
	    conn.setDoOutput(true);     
		OutputStreamWriter wr= new OutputStreamWriter(conn.getOutputStream());
		wr.write(json);
		wr.flush();
		displayResponse(conn);
		} catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (Exception e) {

				e.printStackTrace();

			  }
	}
	
	void delete(String path, String json, String oid)
	{
		try {
		URL url = new URL(baseUrl+"/"+path+"/"+oid);
		
		//POST
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("datetime", getDateTimeString());
	    conn.setRequestProperty("X-AUTH-TOKEN", getAuthorization());
	 //  conn.setUseCaches(false);
	    conn.setDoInput(true); // Triggers POST
	    conn.setDoOutput(true);     
		OutputStreamWriter wr= new OutputStreamWriter(conn.getOutputStream());
		wr.write(json);
		wr.flush();
		displayResponse(conn);
		} catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (Exception e) {

				e.printStackTrace();

			  }
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
 
	void LogoutTest()
	{
		
		
	}
	
	void structurePostTest(String UUID, String name,String address ,String timestamp,String tenantId)
	{
		try {
//		String url="http://rest.verve.house/registerNewUser";
		String url=baseUrl+"/structures";
		URL object=new URL(url);

		HttpURLConnection conn = (HttpURLConnection) object.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestMethod("POST");
		
		
		JSONObject structure=new JSONObject();
		structure.put("addressline1",address);
		structure.put("name",name);
 		structure.put("t", timestamp);//
		structure.put("UUID",UUID );

		JSONArray structures = new JSONArray();
		structures.put(structure);
		JSONObject req=new JSONObject();
		req.put("structures", structures);
		
		
		System.out.println(" structurePost json"+structure.toString());
		LOGGER.error(" structurePost json: "+structure.toString());
		
		OutputStreamWriter wr= new OutputStreamWriter(conn.getOutputStream());
		wr.write(structure.toString());
		wr.flush();
		
		displayResponse(conn);

	 
		}catch(MalformedURLException e){e.printStackTrace();} 
		catch(IOException e){e.printStackTrace();}finally{}
		 
	}
	
 
	
	void displayResponse(HttpURLConnection conn)
	{
	try{	
		for (String k :conn.getHeaderFields().keySet())
		{
			String v = conn.getHeaderField(k);
	 		System.out.println("key="+k+" value="+v);
		}

		StringBuilder sb = new StringBuilder();  
		
		int HttpResult = conn.getResponseCode(); 
		if (HttpResult == HttpURLConnection.HTTP_OK  || HttpResult == HttpURLConnection.HTTP_CREATED   )   {
		    BufferedReader br = new BufferedReader(
		            new InputStreamReader(conn.getInputStream(), "utf-8"));
		    String line = null;  
		    while ((line = br.readLine()) != null) {  
		        sb.append(line + "\n");  
		    }
		    br.close();
		    System.out.println("Response:" + sb.toString());  
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
