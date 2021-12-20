package com.patriciadevitasamara.uas_pyb.UnitTestingRegister;

import com.patriciadevitasamara.uas_pyb.model.register.UserRegisterResponse;

public class RegisterPresenter {
    private RegisterView view;
    private RegisterService service;
    private RegisterCallBack callback;
    private UserRegisterResponse register;

    public RegisterPresenter(RegisterView view, RegisterService service) {
        this.view = view;
        this.service = service;
    }

    public void onRegisterClicked()
    {
        String regexNum = "[0-9]+";
        String regexAlpha = "[a-zA-Z]+";
        String regexAlphaNum = "^[A-Za-z0-9]+$";
        String regexEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String regexAlpha2Words = "[A-Za-z]+\\s+[A-Za-z]+";


        if (view.getName().isEmpty()) {
            view.showNameError("Name tidak boleh kosong");
            return;
        } else if (view.getUsername().isEmpty()) {
            view.showUsernameError("Username tidak boleh kosong");
            return;
        } else if (view.getEmail().isEmpty()) {
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
            service.register(view, register, new RegisterCallBack() {
                @Override
                public void onSuccess(boolean value, UserRegisterResponse register) {
                    view.startMainRegister();
                }

                @Override
                public void onError() {

                }
            });
            return;
        }
    }
}