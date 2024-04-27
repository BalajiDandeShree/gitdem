package com.qa.tests;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GetAPITest extends TestBase{
	TestBase testBase;
	String apiUrl;
	String serviceUrl;
	String url;
	RestClient client;
	
@BeforeMethod
public void setUp() throws ClientProtocolException, IOException {
	testBase = new TestBase();
	 apiUrl = prop.getProperty("URL");
	 serviceUrl = prop.getProperty("serviceURL");
	 url = apiUrl+serviceUrl;
	 System.out.println(url);
	
}

@Test
public void getAPITest() throws ClientProtocolException, IOException {
	 client = new RestClient();
	client.get(url);
}
}
