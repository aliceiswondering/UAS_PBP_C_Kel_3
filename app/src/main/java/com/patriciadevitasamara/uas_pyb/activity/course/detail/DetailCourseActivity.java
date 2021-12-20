package com.patriciadevitasamara.uas_pyb.activity.course.detail;

import static com.patriciadevitasamara.uas_pyb.constant.Constant.EDIT_DATA;
import static com.patriciadevitasamara.uas_pyb.constant.Constant.EXTRA_COURSE_DATA;
import static com.patriciadevitasamara.uas_pyb.constant.Constant.EXTRA_COURSE_DATA_ID;
import static com.patriciadevitasamara.uas_pyb.constant.Constant.STATUS_CRUD;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;

import com.patriciadevitasamara.uas_pyb.activity.course.addedit.AddEditDeleteCourseActivity;
import com.patriciadevitasamara.uas_pyb.databinding.ActivityDetailCourseBinding;
import com.patriciadevitasamara.uas_pyb.model.course.CourseItem;
import com.patriciadevitasamara.uas_pyb.activity.BaseActivity;

public class DetailCourseActivity extends BaseActivity {
    private ActivityDetailCourseBinding binding;
    private DetailCourseViewModel viewModel;
    private CourseItem courseItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(DetailCourseViewModel.class);

        courseItem = getIntent().getParcelableExtra(EXTRA_COURSE_DATA);
        initView(courseItem);
        initListener();
    }

    private void initView(CourseItem courseItem) {
        binding.textViewCourseName.setText(courseItem.getNamaKelas());
        binding.textViewCourseLesson.setText(courseItem.getMataPelajaran());
        binding.textViewCourseCapacity.setText(courseItem.getKapasitas());
        binding.textViewCourseCode.setText(courseItem.getKode());
    }

    private void initListener() {
        binding.buttonEditCourse.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddEditDeleteCourseActivity.class);
            intent.putExtra(STATUS_CRUD, EDIT_DATA);
            intent.putExtra(EXTRA_COURSE_DATA, courseItem);
            resultLauncher.launch(intent);
        });
    }

    private void getDataCourseById(int courseId) {
        showOrHideLoading(true);
        viewModel.getDataCourseById(courseId, this);
        //observe
        viewModel.courseResult.observe(this, courseItem -> {
            showOrHideLoading(false);
            initView(courseItem);
        });

        viewModel.courseError.observe(this, value -> {
            showOrHideLoading(false);
            Toast.makeText(this, "Get course by id gagal", Toast.LENGTH_SHORT).show();
        });
    }

    private void showOrHideLoading(Boolean show) {
        if (show) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
        }
    }

    public final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getData() != null) {
                    int courseId = result.getData().getIntExtra(EXTRA_COURSE_DATA_ID, 0);
                    getDataCourseById(courseId);
                }
            });
}