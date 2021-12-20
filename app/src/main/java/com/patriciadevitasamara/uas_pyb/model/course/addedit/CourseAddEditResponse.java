package com.patriciadevitasamara.uas_pyb.model.course.addedit;

import com.patriciadevitasamara.uas_pyb.model.course.CourseItem;
import com.google.gson.annotations.SerializedName;

public class CourseAddEditResponse {

	@SerializedName("data")
	private CourseItem data;

	@SerializedName("message")
	private String message;

	public void setData(CourseItem data){
		this.data = data;
	}

	public CourseItem getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}