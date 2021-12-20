package com.patriciadevitasamara.uas_pyb.activity.home;

import static com.patriciadevitasamara.uas_pyb.constant.Constant.ADD_DATA;
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
import com.patriciadevitasamara.uas_pyb.databinding.FragmentHomeBinding;
import com.patriciadevitasamara.uas_pyb.utils.SpacesItemDecorations;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private final CourseAdapter adapter = new CourseAdapter();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initRecyclerView();
        getCourseData();
        initListener();
    }

    private void initRecyclerView() {
        binding.rvListKelas.addItemDecoration(new SpacesItemDecorations(
                getResources().getDimensionPixelSize(R.dimen.spacing)
        ));
        binding.rvListKelas.setAdapter(adapter);
        binding.rvListKelas.setHasFixedSize(true);
    }

    private void initListener(){
        binding.fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(binding.getRoot().getContext(), AddEditDeleteCourseActivity.class);
            intent.putExtra(STATUS_CRUD, ADD_DATA);
            resultLauncher.launch(intent);
        });
    }

    private void getCourseData() {
        showOrHideLoading(true);
        homeViewModel.getCourseData(binding.getRoot().getContext());
        //observe
        homeViewModel.courseResult.observe(getViewLifecycleOwner(), response -> {
            showOrHideLoading(false);
            adapter.setData(response);
        });
        homeViewModel.courseError.observe(getViewLifecycleOwner(), value -> {
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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> getCourseData());
}