package com.patriciadevitasamara.uas_pyb.activity.assignment;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.patriciadevitasamara.uas_pyb.constant.Constant;
import com.patriciadevitasamara.uas_pyb.model.assignment.AssignmentItem;
import com.patriciadevitasamara.uas_pyb.model.assignment.AssignmentResponse;
import com.patriciadevitasamara.uas_pyb.api.ApiConfig;
import com.patriciadevitasamara.uas_pyb.api.ApiService;
import com.patriciadevitasamara.uas_pyb.api.HeaderHelper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignmentViewModel extends ViewModel {
    private final ApiService apiService = ApiConfig.getApiService();

    private final MutableLiveData<ArrayList<AssignmentItem>> _assignmentResult = new MutableLiveData<>();
    LiveData<ArrayList<AssignmentItem>> assignmentResult = _assignmentResult;

    private final MutableLiveData<Boolean> _assignmentError = new MutableLiveData<>();
    LiveData<Boolean> assignmentError = _assignmentError;

    public void getDataAssignment(Context context){
        String header = HeaderHelper.generateHeader(context);
        apiService.getAssignment(header).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<AssignmentResponse> call, @NotNull Response<AssignmentResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        _assignmentResult.setValue(response.body().getData());
                    }
                } else {
                    _assignmentError.setValue(true);
                    if (response.body() != null) {
                        Log.e(Constant.COURSE_TAG, "onFailure: " + response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AssignmentResponse> call, @NonNull Throwable t) {
                _assignmentError.setValue(true);
                Log.e(Constant.COURSE_TAG, "onFailure: ${t.message.toString()}");
            }
        });
    }
}
