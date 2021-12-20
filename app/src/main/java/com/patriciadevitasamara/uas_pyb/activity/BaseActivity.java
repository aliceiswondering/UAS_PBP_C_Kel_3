package com.patriciadevitasamara.uas_pyb.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.patriciadevitasamara.uas_pyb.activity.login.LoginActivity;
import com.patriciadevitasamara.uas_pyb.utils.UserPreference;


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserPreference pref = new UserPreference(this);
        String token = pref.getToken();

        if(token.isEmpty()){
            startActivity(new Intent(this, LoginActivity.class));
            finishAffinity();
        }
    }
}