package com.patriciadevitasamara.uas_pyb.activity.course;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.patriciadevitasamara.uas_pyb.api.ApiConfig;
import com.patriciadevitasamara.uas_pyb.api.ApiService;
import com.patriciadevitasamara.uas_pyb.api.HeaderHelper;
import com.patriciadevitasamara.uas_pyb.constant.Constant;
import com.patriciadevitasamara.uas_pyb.model.course.CourseItem;
import com.patriciadevitasamara.uas_pyb.model.course.CourseResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseViewModel extends ViewModel {
    private final ApiService apiService = ApiConfig.getApiService();

    private final MutableLiveData<ArrayList<CourseItem>> _courseResult = new MutableLiveData<>();
    LiveData<ArrayList<CourseItem>> courseResult = _courseResult;

    private final MutableLiveData<Boolean> _courseError = new MutableLiveData<>();
    LiveData<Boolean> courseError = _courseError;

    public void getDataCourse(Context context) {
        String header = HeaderHelper.generateHeader(context);
        apiService.getCourse(header).enqueue(new Callback<CourseResponse>() {
            @Override
            public void onResponse(Call<CourseResponse> call, Response<CourseResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        _courseResult.setValue(response.body().getData());
                    }
                } else {
                    _courseError.setValue(true);
                    if (response.body() != null) {
                        Log.e(Constant.COURSE_TAG, "onFailure: " + response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<CourseResponse> call, Throwable t) {
                    _courseError.setValue(true);
                    Log.e(Constant.COURSE_TAG, "onFailure: ${t.message.toString()}");
            }
        });
    }

}
