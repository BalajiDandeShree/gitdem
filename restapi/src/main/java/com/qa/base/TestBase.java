package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	 public int RESPONSE_STATUS_CODE_200 = 200; 
	 public int RESPONSE_STATUS_CODE_400 = 400; 
	 public int RESPONSE_STATUS_CODE_500 = 500;
	 public int RESPONSE_STATUS_CODE_401 = 401; 
	 public int RESPONSE_STATUS_CODE_201 = 201; 
	
	
	public Properties  prop;
	public  TestBase() {
		prop = new Properties();
		try {
			FileInputStream input = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/config.properties");
			prop.load(input);
			System.out.println("hi i created branch");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
