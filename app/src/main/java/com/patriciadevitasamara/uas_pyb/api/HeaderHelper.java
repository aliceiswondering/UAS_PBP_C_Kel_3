package com.patriciadevitasamara.uas_pyb.api;

import android.content.Context;

import com.patriciadevitasamara.uas_pyb.utils.UserPreference;

public class HeaderHelper {
    public static String generateHeader(Context context){
        UserPreference pref = new UserPreference(context);
        String token = pref.getToken();
        return "Bearer " + token;
    }
}
