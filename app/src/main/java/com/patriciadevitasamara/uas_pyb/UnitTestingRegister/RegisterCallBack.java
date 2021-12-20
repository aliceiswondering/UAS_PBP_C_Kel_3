package com.patriciadevitasamara.uas_pyb.UnitTestingRegister;

import com.patriciadevitasamara.uas_pyb.model.register.UserRegisterResponse;

public interface RegisterCallBack {
    void onSuccess(boolean value, UserRegisterResponse register);
    void onError();
}
