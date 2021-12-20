package com.patriciadevitasamara.uas_pyb.activity.course.detail;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.patriciadevitasamara.uas_pyb.constant.Constant;
import com.patriciadevitasamara.uas_pyb.model.course.CourseItem;
import com.patriciadevitasamara.uas_pyb.model.course.addedit.CourseAddEditResponse;
import com.patriciadevitasamara.uas_pyb.api.ApiConfig;
import com.patriciadevitasamara.uas_pyb.api.ApiService;
import com.patriciadevitasamara.uas_pyb.api.HeaderHelper;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailCourseViewModel extends ViewModel {
    private final ApiService apiService = ApiConfig.getApiService();

    private final MutableLiveData<CourseItem> _courseResult = new MutableLiveData<>();
    LiveData<CourseItem> courseResult = _courseResult;

    private final MutableLiveData<Boolean> _courseError = new MutableLiveData<>();
    LiveData<Boolean> courseError = _courseError;

    public void getDataCourseById(int courseId, Context context){
        String header = HeaderHelper.generateHeader(context);
        apiService.getCourseById(header, courseId).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<CourseAddEditResponse> call, @NotNull Response<CourseAddEditResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        _courseResult.setValue(response.body().getData());
                    }
                } else {
                    _courseError.setValue(true);
                    if (response.body() != null) {
                        Log.e(Constant.COURSE_TAG, "onFailure: get Course by id gagal");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<CourseAddEditResponse> call, @NonNull Throwable t) {
                _courseError.setValue(true);
                Log.e(Constant.COURSE_TAG, "onFailure: ${t.message.toString()}");
            }
        });
    }
}
