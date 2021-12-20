package com.patriciadevitasamara.uas_pyb.activity.assignment.detail;

import static com.patriciadevitasamara.uas_pyb.constant.Constant.EDIT_DATA;
import static com.patriciadevitasamara.uas_pyb.constant.Constant.EXTRA_ASSIGNMENT_DATA;
import static com.patriciadevitasamara.uas_pyb.constant.Constant.EXTRA_ASSIGNMENT_DATA_ID;
import static com.patriciadevitasamara.uas_pyb.constant.Constant.RESULT_CODE_CRUD_ASSIGNMENT;
import static com.patriciadevitasamara.uas_pyb.constant.Constant.STATUS_CRUD;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;

import com.patriciadevitasamara.uas_pyb.databinding.ActivityDetailAssignmentBinding;
import com.patriciadevitasamara.uas_pyb.model.assignment.AssignmentItem;
import com.patriciadevitasamara.uas_pyb.activity.BaseActivity;
import com.patriciadevitasamara.uas_pyb.activity.assignment.addedit.AddEditDeleteAssignmentActivity;

public class DetailAssignmentActivity extends BaseActivity {
    private ActivityDetailAssignmentBinding binding;
    private DetailAssignmentViewModel viewModel;
    private AssignmentItem data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailAssignmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(DetailAssignmentViewModel.class);

        initListener();
        data = getIntent().getParcelableExtra(EXTRA_ASSIGNMENT_DATA);
        initView(data);
    }

    private void initView(AssignmentItem data) {
        binding.textViewAssignmentName.setText(data.getJudul());
        binding.textViewAssignmentMaterial.setText(data.getMateri());
        binding.textViewAssignmentDesc.setText(data.getDeskripsi());
    }

    private void getAssignmentById(int id) {
        showOrHideLoading(true);
        viewModel.getAssignmentById(this, id);
        //observe
        viewModel.assignmentResult.observe(this, assignmentItem -> {
            showOrHideLoading(false);
            initView(assignmentItem);
        });

        viewModel.assignmentError.observe(this, value -> {
            showOrHideLoading(false);
            Toast.makeText(this, "get assignment by id gagal", Toast.LENGTH_SHORT).show();
        });
    }

    private void initListener() {
        binding.buttonEditAssignment.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddEditDeleteAssignmentActivity.class);
            intent.putExtra(STATUS_CRUD, EDIT_DATA);
            intent.putExtra(EXTRA_ASSIGNMENT_DATA, data);
            resultLauncher.launch(intent);
        });
    }

    private void showOrHideLoading(Boolean show) {
        if (show) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
        }
    }

    final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getData() != null) {
                    if (result.getResultCode() == RESULT_CODE_CRUD_ASSIGNMENT) {
                        int assignmentId = result.getData().getIntExtra(EXTRA_ASSIGNMENT_DATA_ID, 0);
                        getAssignmentById(assignmentId);
                    }
                }
            });
}