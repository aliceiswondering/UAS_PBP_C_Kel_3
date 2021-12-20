package com.patriciadevitasamara.uas_pyb.model.register;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse{

	@SerializedName("message")
	private String message;

	@SerializedName("user")
	private UserRegisterResponse user;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setUser(UserRegisterResponse user){
		this.user = user;
	}

	public UserRegisterResponse getUser(){
		return user;
	}
}