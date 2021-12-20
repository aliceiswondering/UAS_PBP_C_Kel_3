package com.patriciadevitasamara.uas_pyb.model.user;

import com.google.gson.annotations.SerializedName;
import com.patriciadevitasamara.uas_pyb.model.login.UserLoginResponse;

import java.util.List;

public class UserResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("user")
    private UserLoginResponse user;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserLoginResponse getUser() {
        return user;
    }

    public void setUser(UserLoginResponse user) {
        user = user;
    }
}
