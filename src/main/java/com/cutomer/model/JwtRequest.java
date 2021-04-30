package com.cutomer.model;

public class JwtRequest {
	private static final long serialVersionUID = 5926468583005150707L;
	
	public JwtRequest(String userName, String password)
	
	{
		
		this.userName=userName;
		
		this.password=password;
		
		
	}
	
	private String userName ;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
