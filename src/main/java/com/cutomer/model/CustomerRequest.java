package com.cutomer.model;

import java.util.Date;

public class CustomerRequest {
	
	private Integer userId;

	private String userName; 
	private Date dateOfBirth; 
	private char gender; 
	private Integer phoneNum;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public Integer getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(Integer phoneNum) {
		this.phoneNum = phoneNum;
	}

}
