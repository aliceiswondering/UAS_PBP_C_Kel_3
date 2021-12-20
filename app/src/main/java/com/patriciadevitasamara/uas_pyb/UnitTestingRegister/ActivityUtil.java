package com.patriciadevitasamara.uas_pyb.UnitTestingRegister;

import android.content.Context;
import android.content.Intent;

import com.patriciadevitasamara.uas_pyb.activity.register.RegisterActivity;

public class ActivityUtil {
    private Context context;

    public ActivityUtil(Context context)
    {
        this.context = context;
    }

    public void startMainRegister()
    {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }
}
