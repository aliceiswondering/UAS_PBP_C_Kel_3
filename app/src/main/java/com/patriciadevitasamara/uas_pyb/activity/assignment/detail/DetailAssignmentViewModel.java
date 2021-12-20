package com.patriciadevitasamara.uas_pyb.activity.assignment.detail;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.patriciadevitasamara.uas_pyb.constant.Constant;
import com.patriciadevitasamara.uas_pyb.model.assignment.AssignmentItem;
import com.patriciadevitasamara.uas_pyb.model.assignment.addedit.AssignmentAddResponse;
import com.patriciadevitasamara.uas_pyb.api.ApiConfig;
import com.patriciadevitasamara.uas_pyb.api.ApiService;
import com.patriciadevitasamara.uas_pyb.api.HeaderHelper;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAssignmentViewModel extends ViewModel {
    private final ApiService apiService = ApiConfig.getApiService();

    private final MutableLiveData<AssignmentItem> _assignmentResult = new MutableLiveData<>();
    LiveData<AssignmentItem> assignmentResult = _assignmentResult;

    private final MutableLiveData<Boolean> _assignmentError = new MutableLiveData<>();
    LiveData<Boolean> assignmentError = _assignmentError;

    public void getAssignmentById(Context context, int id){
        String header = HeaderHelper.generateHeader(context);
        apiService.getAssignmentById(header, id).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<AssignmentAddResponse> call, @NotNull Response<AssignmentAddResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        _assignmentResult.setValue(response.body().getData());
                    }
                } else {
                    _assignmentError.setValue(true);
                    if (response.body() != null) {
                        Log.e(Constant.COURSE_TAG, "onFailure: get assignment by id gagal");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AssignmentAddResponse> call, @NonNull Throwable t) {
                _assignmentError.setValue(true);
                Log.e(Constant.COURSE_TAG, "onFailure: ${t.message.toString()}");
            }
        });
    }
}
