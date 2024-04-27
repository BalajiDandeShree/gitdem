package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient1 {
	
	// get without header
	public CloseableHttpResponse  get(String url) throws ClientProtocolException, IOException {
			
		
		CloseableHttpClient httpClient = HttpClients.createDefault(); // create client
		HttpGet httpGet = new HttpGet(url); // prepare request
		CloseableHttpResponse closeableHttpResponse= httpClient.execute(httpGet); //execute request
		
		return closeableHttpResponse;
		
	}
	// get with header
	public CloseableHttpResponse  get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
				HttpGet httpGet = new HttpGet(url);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		for(Map.Entry<String, String> entry: headerMap.entrySet()) {
			httpGet.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse= httpClient.execute(httpGet);
				return closeableHttpResponse;
		
	}
	
	//Post Method
	public CloseableHttpResponse post(String url, String entityString, HashMap< String, String> headerMap) throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url); // http post request 
		httpPost.setEntity(new StringEntity(entityString)); // adding payload
		
		//Adding header
		for(Map.Entry<String, String> entry: headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(),entry.getValue());
		}
		
		CloseableHttpResponse closeableHttpResponse= httpClient.execute(httpPost);
		return closeableHttpResponse;
	}

}
