package com.qa.data;

public class Users {
	String name ;
	String jobs;
	public Users() {
		
	}
	public Users(String name, String jobs) {
		super();
		this.name = name;
		this.jobs = jobs;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJobs() {
		return jobs;
	}
	public void setJobs(String jobs) {
		this.jobs = jobs;
		System.out.println("This job is set and see for next user ");
	}
	
	

}
