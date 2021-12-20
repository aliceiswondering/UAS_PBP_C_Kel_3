package com.patriciadevitasamara.uas_pyb.activity.assignment;

import static com.patriciadevitasamara.uas_pyb.constant.Constant.ADD_DATA;
import static com.patriciadevitasamara.uas_pyb.constant.Constant.EXTRA_ASSIGNMENT_DATA;
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
import com.patriciadevitasamara.uas_pyb.databinding.FragmentAssignmentBinding;
import com.patriciadevitasamara.uas_pyb.model.assignment.AssignmentItem;
import com.patriciadevitasamara.uas_pyb.activity.assignment.addedit.AddEditDeleteAssignmentActivity;
import com.patriciadevitasamara.uas_pyb.activity.assignment.detail.DetailAssignmentActivity;
import com.patriciadevitasamara.uas_pyb.utils.SpacesItemDecorations;

public class AssignmentFragment extends Fragment implements AssigmentAdapter.AssignmentAdapterListener {
    private AssignmentViewModel viewModel;
    private FragmentAssignmentBinding binding;
    private final AssigmentAdapter adapter = new AssigmentAdapter(this);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);
        binding = FragmentAssignmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initRecyclerView();
        getAssignmentData();
        initListener();
    }

    private void initRecyclerView() {
        binding.rvListAssignment.addItemDecoration(new SpacesItemDecorations(
                getResources().getDimensionPixelSize(R.dimen.spacing)
        ));
        binding.rvListAssignment.setAdapter(adapter);
        binding.rvListAssignment.setHasFixedSize(true);
    }

    private void initListener(){
        binding.fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(binding.getRoot().getContext(), AddEditDeleteAssignmentActivity.class);
            intent.putExtra(STATUS_CRUD, ADD_DATA);
            resultLauncher.launch(intent);
        });
    }

    private void getAssignmentData() {
        showOrHideLoading(true);
        viewModel.getDataAssignment(binding.getRoot().getContext());
        //observe
        viewModel.assignmentResult.observe(getViewLifecycleOwner(), response -> {
            showOrHideLoading(false);
            adapter.setData(response);
        });
        viewModel.assignmentError.observe(getViewLifecycleOwner(), value -> {
            showOrHideLoading(false);
            Toast.makeText(requireContext(), "Gagal mengambil data assignment", Toast.LENGTH_SHORT).show();
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
    public void onItemClick(AssignmentItem item) {
        Intent intent = new Intent(binding.getRoot().getContext(), DetailAssignmentActivity.class);
        intent.putExtra(EXTRA_ASSIGNMENT_DATA, item);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> getAssignmentData());

}