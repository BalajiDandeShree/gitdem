package com.qa.tests;

import java.io.File;
import java.io.IOException;

import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.qa.base.TestBase;
import com.qa.client.RestClient1;
import com.qa.data.Users;
import com.qa.util.TestUtil;

public class PostAPITest  extends TestBase{

	TestBase testBase;
	String apiUrl;
	String serviceUrl;
	String url;
	RestClient1 client;
	CloseableHttpResponse closablehttpResponse;
	
@BeforeMethod
public void setUp() throws ClientProtocolException, IOException {
	testBase = new TestBase();
	 apiUrl = prop.getProperty("URL");
	 serviceUrl = prop.getProperty("serviceURL");
	 url = apiUrl+serviceUrl;
	 System.out.println(url);
	
}

@Test
public void postAPITest() throws StreamWriteException, DatabindException, IOException   {
	client = new RestClient1();
	HashMap< String , String> headerMap  =new HashMap<String, String>();
	 headerMap.put("Content-Type", "application/json");
	 
	 //jackson API
	 ObjectMapper mapper = new ObjectMapper();
	 Users users  = new  Users("Balaji", "XYA");
	 //Object to JSON file
	// mapper.writeValue(new File(System.getProperty("user.dir")+"/src/main/java/com/qa/data/user.json"), users);
	// mapper.writeValue(new File("C:\\Users\\balaji.d\\eclipse-workspace\\restapi\\src\\main\\java\\com\\qa\\data\\user.json"), users);
	 
	 String usersJonString = mapper.writeValueAsString(users);
	 System.out.println(usersJonString);
	 closablehttpResponse = client.post(url, usersJonString, headerMap);
	 int statusCode = closablehttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status "+statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201, "Status code is not");
		
		
		
		//Fetching JSON String
String responseString = EntityUtils.toString(closablehttpResponse.getEntity(),"UTF-8");
		
		JSONObject responseJsonObject = new JSONObject(responseString);
		System.out.println("Response JSON from API" + responseJsonObject);
		
		String name  = TestUtil.getValueByJPath(responseJsonObject, "/name");
		String jobs  = TestUtil.getValueByJPath(responseJsonObject, "/jobs");
		String id  = TestUtil.getValueByJPath(responseJsonObject, "/id");
		String createdAt  = TestUtil.getValueByJPath(responseJsonObject, "/createdAt");
		System.out.println("Name  ="+name);
		System.out.println("Id  ="+id);
		System.out.println("Jobs   ="+jobs);
		System.out.println("CreatedAt  ="+createdAt);
		Assert.assertEquals(name, "Balaji");
		
}

}
