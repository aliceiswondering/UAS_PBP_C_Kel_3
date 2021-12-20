package com.patriciadevitasamara.uas_pyb.activity.register;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.patriciadevitasamara.uas_pyb.constant.Constant;
import com.patriciadevitasamara.uas_pyb.model.register.RegisterResponse;
import com.patriciadevitasamara.uas_pyb.api.ApiConfig;
import com.patriciadevitasamara.uas_pyb.api.ApiService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {
    private final ApiService apiService = ApiConfig.getApiService();

    private final MutableLiveData<RegisterResponse> _registerResult = new MutableLiveData<>();
    LiveData<RegisterResponse> registerResult = _registerResult;

    private final MutableLiveData<Boolean> _registerError = new MutableLiveData<>();
    LiveData<Boolean> registerError = _registerError;


    public void registerData(String name, String username, String email, String password) {
        apiService.register(name, username, email, password).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<RegisterResponse> call, @NotNull Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        _registerResult.setValue(response.body());
                    }
                } else {
                    _registerError.setValue(true);
                    if (response.body() != null) {
                        Log.e(Constant.REGISTER_TAG, "onFailure: " + response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegisterResponse> call, @NonNull Throwable t) {
                _registerError.setValue(true);
                Log.e(Constant.REGISTER_TAG, "onFailure: ${t.message.toString()}");
            }
        });
    }
}
