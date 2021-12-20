package com.patriciadevitasamara.uas_pyb.UnitTestingLogin;

import com.patriciadevitasamara.uas_pyb.api.ApiConfig;
import com.patriciadevitasamara.uas_pyb.api.ApiService;
import com.patriciadevitasamara.uas_pyb.model.login.LoginResponse;
import com.patriciadevitasamara.uas_pyb.model.login.UserLoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginService {
    public void login(final LoginView view, UserLoginResponse user, final LoginCallBack callback)
    {
        ApiService apiService = ApiConfig.getApiService();
        Call<LoginResponse> LoginDAOCall = apiService.login(user.getEmail(), user.getPassword());

        LoginDAOCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body().getMessage().equalsIgnoreCase("berhasil daftar")) {
                    callback.onSuccess(true, response.body().getUser());
                } else {
                    callback.onError();
                    view.showLoginError(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.showErrorResponse(t.getMessage());
                callback.onError();
            }
        });
    }

    public Boolean getValid(final LoginView view, UserLoginResponse user)
    {
        final Boolean[] bool = new Boolean[1];
        login(view, user, new LoginCallBack() {
            @Override
            public void onSuccess(boolean value, UserLoginResponse user) {
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
