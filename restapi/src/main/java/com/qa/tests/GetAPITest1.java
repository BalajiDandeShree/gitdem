package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.base.TestBase;
import com.qa.client.RestClient1;
import com.qa.util.TestUtil;

public class GetAPITest1 extends TestBase{
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

@Test(priority = 1)
public void getAPITest() throws ClientProtocolException, IOException {
	 client = new RestClient1();
	  closablehttpResponse =  client.get(url);
	
	  // checking of status code
	int statusCode = closablehttpResponse.getStatusLine().getStatusCode();
	System.out.println("Status "+statusCode);
	
	Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not");
	
	//getting response		
	String responseString = EntityUtils.toString(closablehttpResponse.getEntity(),"UTF-8");
	
	JSONObject responseJsonObject = new JSONObject(responseString);
	System.out.println("Response JSON from API" + responseJsonObject);
	
	// Per page value
	String perPageValue = TestUtil.getValueByJPath(responseJsonObject, "/per_page");
	System.out.println("Per Page value is ="+perPageValue);
	Assert.assertEquals(Integer.parseInt(perPageValue), 6);
	
	//Total
	String total  = TestUtil.getValueByJPath(responseJsonObject, "/total");
	System.out.println("Total  value is ="+total);
	Assert.assertEquals(Integer.parseInt(total), 12);
	
	//get the value from JSON Aaary
	String firstName = TestUtil.getValueByJPath(responseJsonObject, "/data[0]/first_name");
	String lastName = TestUtil.getValueByJPath(responseJsonObject, "/data[0]/last_name");
	String id = TestUtil.getValueByJPath(responseJsonObject, "/data[0]/id");
	String email = TestUtil.getValueByJPath(responseJsonObject, "/data[0]/email");
	String avatar = TestUtil.getValueByJPath(responseJsonObject, "/data[0]/avatar");
	System.out.println("Last Name "+lastName);
	System.out.println("First Name "+firstName);
	System.out.println("Id "+id);
	System.out.println("email "+email);
	System.out.println("avatar "+avatar);

	
	//geting all headers
	Header headerarray[] = closablehttpResponse.getAllHeaders();
	HashMap<String, String> allHeaders = new HashMap<String, String>();
	for(Header header2 : headerarray) {
		allHeaders.put(header2.getName(),header2.getValue());
	}
	System.out.println("Header Array ------>" + allHeaders);
	
	System.out.println(allHeaders.get("Server"));
}

@Test(priority = 2)
public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
	
	System.out.println("Get with Header");
	 client = new RestClient1();
	 HashMap< String , String> headerMap  =new HashMap<String, String>();
	 headerMap.put("Content-Type", "application/json");
	 
	  closablehttpResponse =  client.get(url);
	  int statusCode = closablehttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status 2 "+statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not");
		
	/*	//getting response		
		String responseString = EntityUtils.toString(closablehttpResponse.getEntity(),"UTF-8");
		
		JSONObject responseJsonObject = new JSONObject(responseString);
		System.out.println("Response JSON from API" + responseJsonObject);
		
		// Per page value
		String perPageValue = TestUtil.getValueByJPath(responseJsonObject, "/per_page");
		System.out.println("Per Page value is ="+perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
		//Total
		String total  = TestUtil.getValueByJPath(responseJsonObject, "/total");
		System.out.println("Total  value is ="+total);
		Assert.assertEquals(Integer.parseInt(total), 12);
		
		//get the value from JSON Aaary
		String firstName = TestUtil.getValueByJPath(responseJsonObject, "/data[0]/first_name");
		String lastName = TestUtil.getValueByJPath(responseJsonObject, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJsonObject, "/data[0]/id");
		String email = TestUtil.getValueByJPath(responseJsonObject, "/data[0]/email");
		String avatar = TestUtil.getValueByJPath(responseJsonObject, "/data[0]/avatar");
		System.out.println("Last Name "+lastName);
		System.out.println("First Name "+firstName);
		System.out.println("Id "+id);
		System.out.println("email "+email);
		System.out.println("avatar "+avatar);

		
		//geting all headers
		Header headerarray[] = closablehttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for(Header header2 : headerarray) {
			allHeaders.put(header2.getName(),header2.getValue());
		}
		System.out.println("Header Array ------>" + allHeaders);
		
		System.out.println(allHeaders.get("Server"));*/
}




}
