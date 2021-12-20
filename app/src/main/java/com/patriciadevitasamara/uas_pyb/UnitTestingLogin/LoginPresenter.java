package com.patriciadevitasamara.uas_pyb.UnitTestingLogin;

import com.patriciadevitasamara.uas_pyb.model.login.UserLoginResponse;

public class LoginPresenter {

    private LoginView view;
    private LoginService service;
    private LoginCallBack callack;
    private UserLoginResponse user;

    public LoginPresenter(LoginView view, LoginService service) {
        this.view = view;
        this.service = service;
    }

    public void onLoginClicked()
    {
        String regexNum = "[0-9]+";
        String regexAlpha = "[a-zA-Z]+";
        String regexAlphaNum = "^[A-Za-z0-9]+$";
        String regexEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String regexAlpha2Words = "[A-Za-z]+\\s+[A-Za-z]+";


        if (view.getEmail().isEmpty()) {
            view.showEmailError("Email tidak boleh kosong");
            return;
        } else if (!view.getEmail().matches(regexEmail)) {
            view.showEmailError("Format Email tidak sesuai dengan aturan");
            return;
        } else if (view.getPassword().isEmpty()) {
            view.showPasswordError("Password tidak boleh kosong");
            return;
        } else if (!view.getPassword().matches(regexAlphaNum)) {
            view.showPasswordError("Format Password tidak sesuai dengan aturan");
            return;
        } else  if (view.getPassword().length() < 6 ){
            view.showPasswordError("Password tidak boleh kurang dari 6 karakter");
            return;
        } else {
            service.login(view, user, new LoginCallBack() {
                @Override
                public void onSuccess(boolean value, UserLoginResponse user) {
                    view.startMainLogin();
                }

                @Override
                public void onError() {

                }
            });
            return;
        }
    }

}

