package com.patriciadevitasamara.uas_pyb.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.patriciadevitasamara.uas_pyb.model.login.LoginResponse;
import com.patriciadevitasamara.uas_pyb.model.login.UserLoginResponse;

public class UserPreference {
    private static final String PREFS_NAME = "user_pref";
    private static final String ID = "name";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String TOKEN = "token";
    private final SharedPreferences preferences;

    public UserPreference(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveUserLogin(LoginResponse value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(ID, value.getUser().getId());
        editor.putString(NAME, value.getUser().getName());
        editor.putString(EMAIL, value.getUser().getEmail());
        editor.putString(TOKEN, value.getAccessToken());
        editor.apply();
    }

    public String getToken() {
        return preferences.getString(TOKEN, "");
    }

    public UserLoginResponse getUser() {
        UserLoginResponse model = new UserLoginResponse();
        model.setId(preferences.getInt(ID, 0));
        model.setName(preferences.getString(NAME, ""));
        model.setEmail(preferences.getString(EMAIL, ""));
        return model;
    }

    public void deleteUserLogin(){
        preferences.edit().clear().apply();
    }
}
