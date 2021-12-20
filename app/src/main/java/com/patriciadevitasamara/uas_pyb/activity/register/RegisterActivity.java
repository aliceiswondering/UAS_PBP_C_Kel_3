package com.patriciadevitasamara.uas_pyb.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.patriciadevitasamara.uas_pyb.R;
import com.patriciadevitasamara.uas_pyb.UnitTestingRegister.ActivityUtil;
import com.patriciadevitasamara.uas_pyb.UnitTestingRegister.RegisterView;
import com.patriciadevitasamara.uas_pyb.databinding.ActivityRegisterBinding;
import com.patriciadevitasamara.uas_pyb.activity.login.LoginActivity;
import com.patriciadevitasamara.uas_pyb.utils.ValidationHelper;

public class RegisterActivity extends AppCompatActivity implements RegisterView {
    private ActivityRegisterBinding binding;
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        binding.buttonRegister.setOnClickListener(v -> validateForm());
        initListener();
    }

    private void validateForm() {
        String name = binding.editTextName.getText().toString().trim();
        String username = binding.editTextUsername.getText().toString().trim();
        String email = binding.editTextEmail.getText().toString().trim();
        String password = binding.editTextPassword.getText().toString().trim();

        Boolean isValidName = validateName(name);
        Boolean isValidUsername = validateUsername(username);
        Boolean isValidEmail = validateEmail(email);
        Boolean isValidPassword = validatePassword(password);

        if (isValidName && isValidUsername && isValidEmail && isValidPassword) {
            register(name, username, email, password);
        }
    }

    private void register(String name, String username, String email, String password) {
        showOrHideLoading(true);
        viewModel.registerData(name, username, email, password);
        //observe
        viewModel.registerResult.observe(this, response -> {
            showOrHideLoading(false);
            Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        viewModel.registerError.observe(this, value -> {
            showOrHideLoading(false);
            showToast("Register gagal");
        });
    }

    private void initListener(){
        binding.textViewToLogin.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
    }

    public Boolean validateName(String name){
        if(ValidationHelper.validateIsNotEmpty(name)){
            return true;
        } else {
            showToast(getString(R.string.validation_is_empty, "nama"));
            return false;
        }
    }

    public Boolean validateUsername(String username){
        if(ValidationHelper.validateIsNotEmpty(username)){
            if(ValidationHelper.validateUsername(username)){
                return true;
            } else {
                showToast(getString(R.string.validation_username));
                return false;
            }
        } else {
            showToast(getString(R.string.validation_is_empty, "username"));
            return false;
        }
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

    private void showOrHideLoading(Boolean show){
        if(show){
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.buttonRegister.setVisibility(View.GONE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
            binding.buttonRegister.setVisibility(View.VISIBLE);
        }
    }

    private void showToast(String value){
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getName()
    {
        return binding.editTextName.getText().toString();
    }
    @Override
    public void showNameError(String message)
    {
        binding.editTextName.setError(message);
    }
    @Override
    public String getUsername()
    {
        return binding.editTextUsername.getText().toString();
    }
    @Override
    public void showUsernameError(String message)
    {
        binding.editTextUsername.setError(message);
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
    public void startMainRegister() {
        new ActivityUtil(this).startMainRegister();
    }
    @Override
    public void showRegisterError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showErrorResponse(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}