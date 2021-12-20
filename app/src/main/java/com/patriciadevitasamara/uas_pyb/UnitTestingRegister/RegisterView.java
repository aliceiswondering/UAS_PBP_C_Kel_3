package com.patriciadevitasamara.uas_pyb.UnitTestingRegister;

public interface RegisterView {

    String getName();
    void showNameError(String message);

    String getUsername();
    void showUsernameError(String message);

    String getEmail();
    void showEmailError(String message);

    String getPassword();
    void showPasswordError(String message);

    void startMainRegister();

    void showRegisterError(String message);
    void showErrorResponse(String message);
}
