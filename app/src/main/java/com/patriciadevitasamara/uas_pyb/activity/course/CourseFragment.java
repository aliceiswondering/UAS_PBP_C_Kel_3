package com.patriciadevitasamara.uas_pyb.activity.course;

import static com.patriciadevitasamara.uas_pyb.constant.Constant.ADD_DATA;
import static com.patriciadevitasamara.uas_pyb.constant.Constant.EXTRA_COURSE_DATA;
import static com.patriciadevitasamara.uas_pyb.constant.Constant.STATUS_CRUD;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.patriciadevitasamara.uas_pyb.R;
import com.patriciadevitasamara.uas_pyb.activity.course.addedit.AddEditDeleteCourseActivity;
import com.patriciadevitasamara.uas_pyb.activity.course.detail.DetailCourseActivity;
import com.patriciadevitasamara.uas_pyb.databinding.FragmentCourseBinding;
import com.patriciadevitasamara.uas_pyb.model.course.CourseItem;
import com.patriciadevitasamara.uas_pyb.utils.SpacesItemDecorations;

public class CourseFragment extends Fragment implements CourseAdapter.CourseAdapterListener {
    private CourseViewModel viewModel;
    private FragmentCourseBinding binding;
    private final CourseAdapter adapter = new CourseAdapter(this);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        binding = FragmentCourseBinding.inflate(inflater, container, false);
        return  binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initRecyclerView();
        getCourseData();
        initListener();
    }

    private void initRecyclerView() {
        binding.rvListKelas.addItemDecoration(new SpacesItemDecorations(getResources().getDimensionPixelSize(R.dimen.spacing)));
        binding.rvListKelas.setAdapter(adapter);
        binding.rvListKelas.setHasFixedSize(true);
    }

    private void initListener() {
        binding.fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(binding.getRoot().getContext(), AddEditDeleteCourseActivity.class);
            intent.putExtra(STATUS_CRUD, ADD_DATA);
            resultLauncher.launch(intent);
        });
    }

    private  void getCourseData() {
        showOrHideLoading(true);
        viewModel.getDataCourse(binding.getRoot().getContext());
        viewModel.courseResult.observe(getViewLifecycleOwner(), response -> {
            showOrHideLoading(false);
            adapter.setData(response);
        });

        viewModel.courseError.observe(getViewLifecycleOwner(), value -> {
            showOrHideLoading(false);
            Toast.makeText(requireContext(), "Gagal mengambil data course", Toast.LENGTH_SHORT).show();
        });
    }

    private void showOrHideLoading(Boolean show) {
        if (show) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(CourseItem item) {
        Intent intent = new Intent(binding.getRoot().getContext(), DetailCourseActivity.class);
        intent.putExtra(EXTRA_COURSE_DATA, item);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> getCourseData());
}

