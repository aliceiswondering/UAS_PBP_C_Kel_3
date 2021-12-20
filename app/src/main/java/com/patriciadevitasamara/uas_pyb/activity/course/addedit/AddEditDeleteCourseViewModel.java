package com.patriciadevitasamara.uas_pyb.activity.course.addedit;

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

public class AddEditDeleteCourseViewModel extends ViewModel {
    private final ApiService apiService = ApiConfig.getApiService();

    private final MutableLiveData<CourseItem> _courseResult = new MutableLiveData<>();
    LiveData<CourseItem> courseResult = _courseResult;

    private final MutableLiveData<Boolean> _courseError = new MutableLiveData<>();
    LiveData<Boolean> courseError = _courseError;

    public void addCourse(Context context, String nama, String mataPelajaran, int kapasitas, String kode){
        String header = HeaderHelper.generateHeader(context);
        apiService.createCourse(header, nama, mataPelajaran, kapasitas, kode).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<CourseAddEditResponse> call, @NotNull Response<CourseAddEditResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        _courseResult.setValue(response.body().getData());
                    }
                } else {
                    _courseError.setValue(true);
                    if (response.body() != null) {
                        Log.e(Constant.COURSE_TAG, "onFailure: create Course gagal");
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

    public void editCourse(Context context, String nama, String mataPelajaran, int kapasitas, String kode, int id){
        String header = HeaderHelper.generateHeader(context);
        apiService.updateCourse(header, id, nama, mataPelajaran, kapasitas, kode).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<CourseAddEditResponse> call, @NotNull Response<CourseAddEditResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        _courseResult.setValue(response.body().getData());
                    }
                } else {
                    _courseError.setValue(true);
                    if (response.body() != null) {
                        Log.e(Constant.COURSE_TAG, "onFailure: update Course gagal");
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

    public void deleteCourse(Context context, int id) {
        String header = HeaderHelper.generateHeader(context);
        apiService.deleteCourse(header, id).enqueue(new Callback<CourseAddEditResponse>() {
            @Override
            public void onResponse(@NotNull Call<CourseAddEditResponse> call, @NotNull Response<CourseAddEditResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        _courseResult.setValue(response.body().getData());
                    }
                } else {
                    _courseError.setValue(true);
                    if (response.body() != null) {
                        Log.e(Constant.COURSE_TAG, "onFailure: delete course gagal");
                    }
                }

            }

            @Override
            public void onFailure(Call<CourseAddEditResponse> call, Throwable t) {
                _courseError.setValue(true);
                Log.e(Constant.COURSE_TAG, "onFailure: ${t.message.toString()}");
            }
        });
    }
}
