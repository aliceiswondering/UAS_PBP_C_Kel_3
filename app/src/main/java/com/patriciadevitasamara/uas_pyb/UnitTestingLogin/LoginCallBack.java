package com.patriciadevitasamara.uas_pyb.UnitTestingLogin;

import com.patriciadevitasamara.uas_pyb.model.login.UserLoginResponse;

public interface LoginCallBack {
    void onSuccess(boolean value, UserLoginResponse user);
    void onError();
}
