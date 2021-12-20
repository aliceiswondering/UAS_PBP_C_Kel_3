package com.patriciadevitasamara.uas_pyb.activity.course.addedit;

import static com.patriciadevitasamara.uas_pyb.constant.Constant.ADD_DATA;
import static com.patriciadevitasamara.uas_pyb.constant.Constant.EXTRA_COURSE_DATA;
import static com.patriciadevitasamara.uas_pyb.constant.Constant.EXTRA_COURSE_DATA_ID;
import static com.patriciadevitasamara.uas_pyb.constant.Constant.RESULT_CODE_CRUD_ASSIGNMENT;
import static com.patriciadevitasamara.uas_pyb.constant.Constant.STATUS_CRUD;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.patriciadevitasamara.uas_pyb.R;
import com.patriciadevitasamara.uas_pyb.databinding.ActivityAddEditDeleteCourseBinding;
import com.patriciadevitasamara.uas_pyb.model.course.CourseItem;
import com.patriciadevitasamara.uas_pyb.activity.BaseActivity;
import com.patriciadevitasamara.uas_pyb.activity.course.detail.DetailCourseActivity;
import com.patriciadevitasamara.uas_pyb.activity.home.HomeFragment;

public class AddEditDeleteCourseActivity extends BaseActivity {
    private ActivityAddEditDeleteCourseBinding binding;
    private com.patriciadevitasamara.uas_pyb.activity.course.addedit.AddEditDeleteCourseViewModel viewModel;
    private CourseItem data;
    private String pageStatus = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditDeleteCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(com.patriciadevitasamara.uas_pyb.activity.course.addedit.AddEditDeleteCourseViewModel.class);
        pageStatus = getIntent().getStringExtra(STATUS_CRUD);

        iniView(pageStatus);
        initListener();
    }

    public void iniView(String pageStatus){
        if(pageStatus.equals(ADD_DATA)){
            binding.textViewPageTitle.setText(getString(R.string.add_course));
            binding.linearLayoutEdit.setVisibility(View.GONE);
        } else {
            binding.textViewPageTitle.setText(getString(R.string.edit_course));
            binding.linearLayoutEdit.setVisibility(View.VISIBLE);
            binding.buttonAdd.setVisibility(View.GONE);

            data = getIntent().getParcelableExtra(EXTRA_COURSE_DATA);
            if(data != null){
                binding.editTextCourseName.setText(data.getNamaKelas());
                binding.editTextCourseLesson.setText(data.getMataPelajaran());
                binding.editTextCourseCapacity.setText(data.getKapasitas());
                binding.editTextCourseCode.setText(data.getKode());
            }
        }
    }

    private void initListener(){
        binding.editTextCourseCapacity.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_CLASS_NUMBER);
        binding.editTextCourseCapacity.setKeyListener(DigitsKeyListener.getInstance(getString(R.string.number_digit_only)));
        binding.buttonAdd.setOnClickListener(v -> validateForm());
        binding.buttonEdit.setOnClickListener(v -> validateForm());
        binding.buttonDelete.setOnClickListener(v -> deleteCourse());
    }

    private void validateForm(){
        String name = binding.editTextCourseName.getText().toString().trim();
        String mataPelajaran = binding.editTextCourseLesson.getText().toString().trim();
        String kapasitas = binding.editTextCourseCapacity.getText().toString().trim();
        String kode = binding.editTextCourseCode.getText().toString().trim();

        if(!name.isEmpty() && !mataPelajaran.isEmpty() && !kapasitas.isEmpty() && !kode.isEmpty()){
            if(pageStatus.equals(ADD_DATA)){
                addCourse(name, mataPelajaran, Integer.parseInt(kapasitas), kode);
            } else {
                editCourse(name, mataPelajaran, Integer.parseInt(kapasitas), kode);
            }
        } else {
            Toast.makeText(this, "Semua form harus diisi", Toast.LENGTH_SHORT).show();
        }
    }

    private void addCourse(String nama, String mataPelajaran, int kapasitas, String kode){
        showOrHideLoading(true);
        viewModel.addCourse(this, nama, mataPelajaran, kapasitas, kode);
        //observe
        viewModel.courseResult.observe(this, courseItem -> {
            showOrHideLoading(false);
            //Back to home
            Intent intent = new Intent(this, HomeFragment.class);
            setResult(RESULT_CODE_CRUD_ASSIGNMENT, intent);
            finish();
        });

        viewModel.courseError.observe(this, value -> {
            showOrHideLoading(false);
            Toast.makeText(this, "add course gagal", Toast.LENGTH_SHORT).show();
        });
    }

    private void editCourse(String nama, String mataPelajaran, int kapasitas, String kode){
        showOrHideLoading(true);
        viewModel.editCourse(this, nama, mataPelajaran, kapasitas, kode, data.getId());
        //observe
        viewModel.courseResult.observe(this, courseItem -> {
            showOrHideLoading(false);
            //Back to course detail
            Intent intent = new Intent(this, DetailCourseActivity.class);
            intent.putExtra(EXTRA_COURSE_DATA_ID, data.getId());
            setResult(RESULT_CODE_CRUD_ASSIGNMENT, intent);
            finish();
        });

        viewModel.courseError.observe(this, value -> {
            showOrHideLoading(false);
            Toast.makeText(this, "update course gagal", Toast.LENGTH_SHORT).show();
        });
    }

    public void deleteCourse(){
        showOrHideLoading(true);
        viewModel.deleteCourse(this, data.getId());
        //observe
        viewModel.courseResult.observe(this, courseItem -> {
            showOrHideLoading(false);
            //Back to home
            Intent intent = new Intent(this, HomeFragment.class);
            setResult(RESULT_CODE_CRUD_ASSIGNMENT, intent);
            finish();
        });

        viewModel.courseError.observe(this, value -> {
            showOrHideLoading(false);
            Toast.makeText(this, "delete course gagal", Toast.LENGTH_SHORT).show();
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