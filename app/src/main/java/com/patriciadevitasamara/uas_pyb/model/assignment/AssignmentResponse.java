package com.patriciadevitasamara.uas_pyb.model.assignment;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AssignmentResponse{

	@SerializedName("data")
	private ArrayList<AssignmentItem> data;

	@SerializedName("message")
	private String message;

	public void setData(ArrayList<AssignmentItem> data){
		this.data = data;
	}

	public ArrayList<AssignmentItem> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}