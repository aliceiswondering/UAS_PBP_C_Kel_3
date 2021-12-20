package com.patriciadevitasamara.uas_pyb.model.course;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CourseResponse{

	@SerializedName("data")
	private ArrayList<CourseItem> data;

	@SerializedName("message")
	private String message;

	public void setData(ArrayList<CourseItem> data){
		this.data = data;
	}

	public ArrayList<CourseItem> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}