package com.patriciadevitasamara.uas_pyb.activity.login;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.patriciadevitasamara.uas_pyb.constant.Constant;
import com.patriciadevitasamara.uas_pyb.model.login.LoginResponse;
import com.patriciadevitasamara.uas_pyb.api.ApiConfig;
import com.patriciadevitasamara.uas_pyb.api.ApiService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    private final ApiService apiService = ApiConfig.getApiService();

    private final MutableLiveData<LoginResponse> _loginResult = new MutableLiveData<>();
    LiveData<LoginResponse> loginResult = _loginResult;

    private final MutableLiveData<Boolean> _loginError = new MutableLiveData<>();
    LiveData<Boolean> loginError = _loginError;

    public void login(String email, String password) {
        apiService.login(email, password).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<LoginResponse> call, @NotNull Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        _loginResult.setValue(response.body());
                    }
                } else {
                    _loginError.setValue(true);
                    if (response.body() != null) {
                        Log.e(Constant.LOGIN_TAG, "onFailure: " + response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                _loginError.setValue(true);
                Log.e(Constant.LOGIN_TAG, "onFailure: ${t.message.toString()}");
            }
        });
    }
}
