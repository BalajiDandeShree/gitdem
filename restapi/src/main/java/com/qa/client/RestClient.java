package com.qa.client;

import java.io.IOException;


import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	
	public void get(String url) throws ClientProtocolException, IOException {
		
		
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		CloseableHttpResponse httpResponse= httpClient.execute(httpGet);
		//getting status code
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status "+statusCode);
		
		//getting response		
		String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		
		JSONObject responseJsonObject = new JSONObject(responseString);
		System.out.println("Response JSON from API" + responseJsonObject);
		
		//geting all headers
		Header headerarray[] = httpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for(Header header2 : headerarray) {
			allHeaders.put(header2.getName(),header2.getValue());
		}
		System.out.println("Header Array ------>" + allHeaders);
		
	}

}
