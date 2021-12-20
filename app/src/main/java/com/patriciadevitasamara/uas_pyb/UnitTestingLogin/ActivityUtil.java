package com.patriciadevitasamara.uas_pyb.UnitTestingLogin;

import android.content.Context;
import android.content.Intent;

import com.patriciadevitasamara.uas_pyb.activity.login.LoginActivity;

public class ActivityUtil {
    private Context context;

    public ActivityUtil(Context context)
    {
        this.context = context;
    }

    public void startMainLogin()
    {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

}