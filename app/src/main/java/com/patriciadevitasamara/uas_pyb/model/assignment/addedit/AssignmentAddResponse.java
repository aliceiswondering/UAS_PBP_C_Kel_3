package com.patriciadevitasamara.uas_pyb.model.assignment.addedit;

import com.patriciadevitasamara.uas_pyb.model.assignment.AssignmentItem;
import com.google.gson.annotations.SerializedName;

public class AssignmentAddResponse {
    @SerializedName("data")
    private AssignmentItem data;

    @SerializedName("message")
    private String message;

    public void setData(AssignmentItem data){
        this.data = data;
    }

    public AssignmentItem getData(){
        return data;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
