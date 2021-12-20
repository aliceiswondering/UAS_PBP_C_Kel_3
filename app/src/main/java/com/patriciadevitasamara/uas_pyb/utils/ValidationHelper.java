package com.patriciadevitasamara.uas_pyb.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationHelper {
    private static final String EMAIL = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";

    public static Boolean validateIsNotEmpty(String value){
        return !value.isEmpty();
    }

    public static Boolean validateEmail(String email){
        Matcher m = Pattern.compile(EMAIL).matcher(email);
        return m.find();
    }

    public static Boolean validateUsername(String username){
        return username.length() > 5;
    }
}