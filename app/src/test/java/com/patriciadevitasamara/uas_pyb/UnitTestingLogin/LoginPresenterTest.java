package com.patriciadevitasamara.uas_pyb.UnitTestingLogin;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {
    @Mock
    private LoginView view;
    @Mock
    private LoginService service;
    private LoginPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new LoginPresenter(view, service);
    }

    @Test
    public void shouldShowErrorMessageWhenEmailIsEmpty() throws Exception {
        when(view.getEmail()) .thenReturn("");
        System.out.println("Testing Pertama : Inputan Email Kosong");
        System.out.println("Email : " + view.getEmail());
        presenter.onLoginClicked();
        verify(view).showEmailError("Email tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenEmailIsNotUseEmailFormat() throws  Exception {
        System.out.println("\n\n" + "Testing Pertama : Inputan Email Kosong");
        when(view.getEmail()) .thenReturn("gilangp");
        System.out.println("Email : " + view.getEmail());
        presenter.onLoginClicked();
        verify(view).showEmailError("Format Email tidak sesuai dengan aturan");
    }

    @Test
    public void shouldShowErrorMessageWhenEmailIsNotUseEmailFormat2() throws  Exception {
        System.out.println("\n\n" + "Testing Pertama : Inputan Email Kosong");
        when(view.getEmail()) .thenReturn("gilangp@gmail.Com");
        System.out.println("Email : " + view.getEmail());

        presenter.onLoginClicked();
        verify(view).showEmailError("Format Email tidak sesuai dengan aturan");
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordIsEmpty() throws Exception {
        System.out.println("\n\n" + "Testing Kedua : Inputan Password Kosong");
        when(view.getEmail()) .thenReturn("gilangp@gmail.com");
        System.out.println("Email : "+view.getEmail());

        when(view.getPassword()) .thenReturn("");
        System.out.println("Password : "+view.getPassword());

        presenter.onLoginClicked();
        verify(view).showPasswordError("Password tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordIsNotAlphaNum() throws Exception{
        System.out.println("\n\n" + "Testing Kedua : Inputan Password Kosong");
        when(view.getEmail()) .thenReturn("gilangp@gmail.com");
        System.out.println("Email : "+view.getEmail());

        when(view.getPassword()) .thenReturn("gilang_");
        System.out.println("Password : "+view.getPassword());

        presenter.onLoginClicked();
        verify(view).showPasswordError("Format Password tidak sesuai dengan aturan");
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordIsLess6 () throws Exception {
        System.out.println("\n\n" + "Testing Kedua : Inputan Password Kosong");
        when(view.getEmail()) .thenReturn("gilangp@gmail.com");
        System.out.println("Email : "+view.getEmail());

        when(view.getPassword()) .thenReturn("gil");
        System.out.println("Password : "+view.getPassword());

        presenter.onLoginClicked();
        verify(view).showPasswordError("Password tidak boleh kurang dari 6 karakter");
    }

}