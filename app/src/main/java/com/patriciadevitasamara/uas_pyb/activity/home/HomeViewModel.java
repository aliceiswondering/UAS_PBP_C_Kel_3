package com.patriciadevitasamara.uas_pyb.activity.home;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.patriciadevitasamara.uas_pyb.constant.Constant;
import com.patriciadevitasamara.uas_pyb.model.course.CourseItem;
import com.patriciadevitasamara.uas_pyb.model.course.CourseResponse;
import com.patriciadevitasamara.uas_pyb.api.ApiConfig;
import com.patriciadevitasamara.uas_pyb.api.ApiService;
import com.patriciadevitasamara.uas_pyb.api.HeaderHelper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    private final ApiService apiService = ApiConfig.getApiService();

    private final MutableLiveData<ArrayList<CourseItem>> _courseResult = new MutableLiveData<>();
    LiveData<ArrayList<CourseItem>> courseResult = _courseResult;

    private final MutableLiveData<Boolean> _courseError = new MutableLiveData<>();
    LiveData<Boolean> courseError = _courseError;

    public void getCourseData(Context context){
        String header = HeaderHelper.generateHeader(context);
        apiService.getCourse(header).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<CourseResponse> call, @NotNull Response<CourseResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        _courseResult.setValue(response.body().getData());
                    }
                } else {
                    if (response.body() != null) {
                        _courseError.setValue(true);
                        Log.e(Constant.COURSE_TAG, "onFailure: " + response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<CourseResponse> call, @NonNull Throwable t) {
                _courseError.setValue(true);
                Log.e(Constant.COURSE_TAG, "onFailure: ${t.message.toString()}");
            }
        });
    }
}