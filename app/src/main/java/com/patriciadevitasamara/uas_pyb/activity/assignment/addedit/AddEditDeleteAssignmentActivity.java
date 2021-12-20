package com.patriciadevitasamara.uas_pyb.activity.assignment.addedit;

import static com.patriciadevitasamara.uas_pyb.constant.Constant.ADD_DATA;
import static com.patriciadevitasamara.uas_pyb.constant.Constant.EXTRA_ASSIGNMENT_DATA;
import static com.patriciadevitasamara.uas_pyb.constant.Constant.EXTRA_ASSIGNMENT_DATA_ID;
import static com.patriciadevitasamara.uas_pyb.constant.Constant.RESULT_CODE_CRUD_ASSIGNMENT;
import static com.patriciadevitasamara.uas_pyb.constant.Constant.STATUS_CRUD;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.patriciadevitasamara.uas_pyb.R;
import com.patriciadevitasamara.uas_pyb.databinding.ActivityAddEditDeleteAssignmentBinding;
import com.patriciadevitasamara.uas_pyb.model.assignment.AssignmentItem;
import com.patriciadevitasamara.uas_pyb.activity.BaseActivity;
import com.patriciadevitasamara.uas_pyb.activity.assignment.AssignmentFragment;
import com.patriciadevitasamara.uas_pyb.activity.assignment.detail.DetailAssignmentActivity;

public class AddEditDeleteAssignmentActivity extends BaseActivity {
    private ActivityAddEditDeleteAssignmentBinding binding;
    private AddEditDeleteAssignmentViewModel viewModel;
    private int assignmentId = 0;
    private String pageStatus = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditDeleteAssignmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(AddEditDeleteAssignmentViewModel.class);
        pageStatus = getIntent().getStringExtra(STATUS_CRUD);

        iniView(pageStatus);
        initListener();
    }

    public void iniView(String pageStatus) {
        if (pageStatus.equals(ADD_DATA)) {
            binding.textViewPageTitle.setText(getString(R.string.add_assignment));
            binding.linearLayoutEdit.setVisibility(View.GONE);
        } else {
            binding.textViewPageTitle.setText(getString(R.string.edit_assignment));
            binding.linearLayoutEdit.setVisibility(View.VISIBLE);
            binding.buttonAdd.setVisibility(View.GONE);

            AssignmentItem data = getIntent().getParcelableExtra(EXTRA_ASSIGNMENT_DATA);
            if (data != null) {
                assignmentId = data.getId();
                binding.editTextAssignmentName.setText(data.getJudul());
                binding.editTextAssignmentMaterial.setText(data.getMateri());
                binding.editTextAssignmentDesc.setText(data.getDeskripsi());
            }
        }
    }

    private void initListener() {
        binding.buttonAdd.setOnClickListener(v -> validateForm());
        binding.buttonEdit.setOnClickListener(v -> validateForm());
        binding.buttonDelete.setOnClickListener(v -> deleteAssignment());
    }

    private void validateForm() {
        String name = binding.editTextAssignmentName.getText().toString().trim();
        String material = binding.editTextAssignmentMaterial.getText().toString().trim();
        String desc = binding.editTextAssignmentDesc.getText().toString().trim();

        if (!name.isEmpty() && !material.isEmpty() && !desc.isEmpty()) {
            if (pageStatus.equals(ADD_DATA)) {
                addAssignment(name, material, desc);
            } else {
                editAssignment(name, material, desc);
            }
        } else {
            Toast.makeText(this, "Semua form harus diisi", Toast.LENGTH_SHORT).show();
        }
    }

    private void addAssignment(String name, String material, String desc) {
        showOrHideLoading(true);
        viewModel.addAssigment(this, name, material, desc);
        //observe
        viewModel.assignmentResult.observe(this, assignmentItem -> {
            showOrHideLoading(false);
            //Back to home
            Intent intent = new Intent(this, AssignmentFragment.class);
            setResult(RESULT_CODE_CRUD_ASSIGNMENT, intent);
            finish();
        });
        viewModel.assignmentError.observe(this, value -> {
            showOrHideLoading(false);
            Toast.makeText(this, "add assignment gagal", Toast.LENGTH_SHORT).show();
        });
    }

    private void editAssignment(String name, String material, String desc) {
        showOrHideLoading(true);
        viewModel.editAssigment(this, name, material, desc, assignmentId);
        //observe
        viewModel.assignmentResult.observe(this, assignmentItem -> {
            showOrHideLoading(false);
            //Back to assignment detail
            Intent intent = new Intent(this, DetailAssignmentActivity.class);
            intent.putExtra(EXTRA_ASSIGNMENT_DATA_ID, assignmentId);
            setResult(RESULT_CODE_CRUD_ASSIGNMENT, intent);
            finish();
        });
        viewModel.assignmentError.observe(this, value -> {
            showOrHideLoading(false);
            Toast.makeText(this, "update assignment gagal", Toast.LENGTH_SHORT).show();
        });
    }

    public void deleteAssignment() {
        showOrHideLoading(true);
        viewModel.deleteAssigment(this, assignmentId);
        //observe
        viewModel.assignmentResult.observe(this, assignmentItem -> {
            showOrHideLoading(false);
            //Back to home
            Intent intent = new Intent(this, AssignmentFragment.class);
            setResult(RESULT_CODE_CRUD_ASSIGNMENT, intent);
            finish();
        });
        viewModel.assignmentError.observe(this, value -> {
            showOrHideLoading(false);
            Toast.makeText(this, "delete assignment gagal", Toast.LENGTH_SHORT).show();
        });
    }

    private void showOrHideLoading(Boolean show) {
        if (show) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
        }
    }
}