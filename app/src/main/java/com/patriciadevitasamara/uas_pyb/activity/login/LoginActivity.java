package com.patriciadevitasamara.uas_pyb.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.patriciadevitasamara.uas_pyb.R;
import com.patriciadevitasamara.uas_pyb.UnitTestingLogin.ActivityUtil;
import com.patriciadevitasamara.uas_pyb.UnitTestingLogin.LoginView;
import com.patriciadevitasamara.uas_pyb.databinding.ActivityLoginBinding;
import com.patriciadevitasamara.uas_pyb.model.login.LoginResponse;
import com.patriciadevitasamara.uas_pyb.activity.MainActivity;
import com.patriciadevitasamara.uas_pyb.activity.register.RegisterActivity;
import com.patriciadevitasamara.uas_pyb.utils.UserPreference;
import com.patriciadevitasamara.uas_pyb.utils.ValidationHelper;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        initListener();
    }

    private void initListener(){
        binding.buttonLogin.setOnClickListener(v -> validateForm());
        binding.textViewToRegister.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
    }

    private void validateForm() {
        String email = binding.editTextEmail.getText().toString().trim();
        String password = binding.editTextPassword.getText().toString().trim();

        Boolean isValidEmail = validateEmail(email);
        Boolean isValidPassword = validatePassword(password);

        if (isValidEmail && isValidPassword) {
            showOrHideLoading(true);
            login(email, password);
        }
    }

    private void login(String email, String password) {
        viewModel.login(email, password);
        //observe
        viewModel.loginResult.observe(this, response -> {
            showOrHideLoading(false);
            saveToLocal(response);
            Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finishAffinity();
        });

        viewModel.loginError.observe(this, value -> {
            showOrHideLoading(false);
            showToast("Login gagal");
        });
    }

    private void saveToLocal(LoginResponse value){
        UserPreference pref = new UserPreference(this);
        pref.saveUserLogin(value);
    }

    public Boolean validateEmail(String email){
        if(ValidationHelper.validateIsNotEmpty(email)){
            if(ValidationHelper.validateEmail(email)){
                return true;
            } else {
                showToast(getString(R.string.validation_email));
                return false;
            }
        } else {
            showToast(getString(R.string.validation_is_empty, "email"));
            return false;
        }
    }

    public Boolean validatePassword(String password){
        if(ValidationHelper.validateIsNotEmpty(password)){
            return true;
        } else {
            showToast(getString(R.string.validation_is_empty, "password"));
            return false;
        }
    }

    private void showOrHideLoading(Boolean show) {
        if (show) {
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.buttonLogin.setVisibility(View.GONE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
            binding.buttonLogin.setVisibility(View.VISIBLE);
        }
    }

    private void showToast(String value){
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getEmail()
    {
        return binding.editTextEmail.getText().toString();
    }
    @Override
    public void showEmailError(String message)
    {
        binding.editTextEmail.setError(message);
    }
    @Override
    public String getPassword()
    {
        return binding.editTextPassword.getText().toString();
    }
    @Override
    public void showPasswordError(String message)
    {
        binding.editTextPassword.setError(message);
    }
    @Override
    public void startMainLogin() {
        new ActivityUtil(this).startMainLogin();
    }
    @Override
    public void showLoginError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showErrorResponse(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}