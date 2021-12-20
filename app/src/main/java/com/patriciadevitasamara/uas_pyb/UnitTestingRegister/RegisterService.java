package com.patriciadevitasamara.uas_pyb.UnitTestingRegister;

import com.patriciadevitasamara.uas_pyb.api.ApiConfig;
import com.patriciadevitasamara.uas_pyb.api.ApiService;
import com.patriciadevitasamara.uas_pyb.model.register.RegisterResponse;
import com.patriciadevitasamara.uas_pyb.model.register.UserRegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterService {

    public void register(final RegisterView view, UserRegisterResponse register, final RegisterCallBack callback)
    {
        ApiService apiService = ApiConfig.getApiService();
        Call<RegisterResponse> registerDAOCall = apiService.register(register.getName(), register.getUsername(), register.getEmail(), register.getPassword());

        registerDAOCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.body().getMessage().equalsIgnoreCase("berhasil daftar")) {
                    callback.onSuccess(true, response.body().getUser());
                } else {
                    callback.onError();
                    view.showRegisterError(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                view.showErrorResponse(t.getMessage());
                callback.onError();
            }
        });
    }

    public Boolean getValid(final RegisterView view, UserRegisterResponse register)
    {
        final Boolean[] bool = new Boolean[1];
        register(view, register, new RegisterCallBack() {
            @Override
            public void onSuccess(boolean value, UserRegisterResponse register) {
                bool[0] = true;
            }

            @Override
            public void onError() {
                bool[0] = false;
            }
        });
        return bool[0];
    }
}