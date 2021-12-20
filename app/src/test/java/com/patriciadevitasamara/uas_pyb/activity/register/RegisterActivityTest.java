package com.patriciadevitasamara.uas_pyb.activity.register;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.patriciadevitasamara.uas_pyb.UnitTestingRegister.RegisterPresenter;
import com.patriciadevitasamara.uas_pyb.UnitTestingRegister.RegisterService;
import com.patriciadevitasamara.uas_pyb.UnitTestingRegister.RegisterView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RegisterActivityTest {

    @Mock
    private RegisterView view;
    @Mock
    private RegisterService service;
    private RegisterPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new RegisterPresenter(view, service);
    }

    @Test
    public void shouldShowErrorMessageWhenNameIsEmpty() throws Exception {
        when(view.getName()) .thenReturn("");
        System.out.println("Testing Pertama : Inputan Name Kosong");
        System.out.println("Name : " + view.getName());
        presenter.onRegisterClicked();
        verify(view).showNameError("Name tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenUsernameIsEmpty() throws Exception {
        System.out.println("\n\n" + "Testing Kedua: Inputan Username Kosong");
        when(view.getName()) .thenReturn("Gilang");
        System.out.println("Name : " + view.getName());

        when(view.getUsername()) .thenReturn("");
        System.out.println("Username : "+view.getUsername());

        presenter.onRegisterClicked();
        verify(view).showUsernameError("Username tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenEmailIsEmpty() throws Exception {
        System.out.println("\n\n" + "Testing Ketiga: Inputan Email Kosong");
        when(view.getName()) .thenReturn("Gilang");
        System.out.println("Name : " + view.getName());

        when(view.getUsername()) .thenReturn("gilang11");
        System.out.println("Username : "+view.getUsername());

        when(view.getEmail()) .thenReturn("");
        System.out.println("Email : " + view.getEmail());

        presenter.onRegisterClicked();
        verify(view).showEmailError("Email tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenEmailIsNotUseEmailFormat() throws  Exception {
        System.out.println("\n\n" + "Testing Ketiga: Inputan Email Kosong");
        when(view.getName()) .thenReturn("Gilang");
        System.out.println("Name : " + view.getName());

        when(view.getUsername()) .thenReturn("gilang11");
        System.out.println("Username : "+view.getUsername());

        when(view.getEmail()) .thenReturn("gilangp");
        System.out.println("Email : " + view.getEmail());

        presenter.onRegisterClicked();
        verify(view).showEmailError("Format Email tidak sesuai dengan aturan");
    }

    @Test
    public void shouldShowErrorMessageWhenEmailIsNotUseEmailFormat2() throws  Exception {
        System.out.println("\n\n" + "Testing Ketiga: Inputan Email Kosong");
        when(view.getName()) .thenReturn("Gilang");
        System.out.println("Name : " + view.getName());

        when(view.getUsername()) .thenReturn("gilang11");
        System.out.println("Username : "+view.getUsername());

        when(view.getEmail()) .thenReturn("gilangp@gmail.Com");
        System.out.println("Email : " + view.getEmail());

        presenter.onRegisterClicked();
        verify(view).showEmailError("Format Email tidak sesuai dengan aturan");
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordIsEmpty() throws Exception {
        System.out.println("\n\n" + "Testing Keempat: Inputan Password Kosong");
        when(view.getName()) .thenReturn("Gilang");
        System.out.println("Name : " + view.getName());

        when(view.getUsername()) .thenReturn("gilang11");
        System.out.println("Username : "+view.getUsername());

        when(view.getEmail()) .thenReturn("gilangp@gmail.com");
        System.out.println("Email : " + view.getEmail());

        when(view.getPassword()) .thenReturn("");
        System.out.println("Password : "+view.getPassword());

        presenter.onRegisterClicked();
        verify(view).showPasswordError("Password tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordIsNotAlphaNum() throws Exception{
        System.out.println("\n\n" + "Testing Keempat: Inputan Password Kosong");
        when(view.getName()) .thenReturn("Gilang");
        System.out.println("Name : " + view.getName());

        when(view.getUsername()) .thenReturn("gilang11");
        System.out.println("Username : "+view.getUsername());

        when(view.getEmail()) .thenReturn("gilangp@gmail.com");
        System.out.println("Email : " + view.getEmail());


        when(view.getPassword()) .thenReturn("gilang_");
        System.out.println("Password : "+view.getPassword());

        presenter.onRegisterClicked();
        verify(view).showPasswordError("Format Password tidak sesuai dengan aturan");
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordIsLess6 () throws Exception {
        System.out.println("\n\n" + "Testing Keempat: Inputan Password Kosong");
        when(view.getName()) .thenReturn("Gilang");
        System.out.println("Name : " + view.getName());

        when(view.getUsername()) .thenReturn("gilang11");
        System.out.println("Username : "+view.getUsername());

        when(view.getEmail()) .thenReturn("gilangp@gmail.com");
        System.out.println("Email : " + view.getEmail());


        when(view.getPassword()) .thenReturn("gil");
        System.out.println("Password : "+view.getPassword());

        presenter.onRegisterClicked();
        verify(view).showPasswordError("Password tidak boleh kurang dari 6 karakter");
    }

}