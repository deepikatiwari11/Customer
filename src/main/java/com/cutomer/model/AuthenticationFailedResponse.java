package com.cutomer.model;

import java.io.Serializable;

public class AuthenticationFailedResponse implements Serializable {
	
	private static final long serialVersionUID = -8091879091924046844L;
	
	private Integer status;
	private String message;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}}