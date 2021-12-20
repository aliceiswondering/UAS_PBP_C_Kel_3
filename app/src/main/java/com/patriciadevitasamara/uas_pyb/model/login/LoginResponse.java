package com.patriciadevitasamara.uas_pyb.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("message")
	private String message;

	@SerializedName("token_type")
	private String tokenType;

	@SerializedName("user")
	private UserLoginResponse userLoginResponse;

	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setTokenType(String tokenType){
		this.tokenType = tokenType;
	}

	public String getTokenType(){
		return tokenType;
	}

	public void setUser(UserLoginResponse userLoginResponse){
		this.userLoginResponse = userLoginResponse;
	}

	public UserLoginResponse getUser(){
		return userLoginResponse;
	}
}