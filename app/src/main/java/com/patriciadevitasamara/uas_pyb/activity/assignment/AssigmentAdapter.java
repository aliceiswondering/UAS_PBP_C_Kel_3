package com.patriciadevitasamara.uas_pyb.activity.assignment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.patriciadevitasamara.uas_pyb.databinding.ItemAssignmentBinding;
import com.patriciadevitasamara.uas_pyb.model.assignment.AssignmentItem;

import java.util.ArrayList;

public class AssigmentAdapter extends RecyclerView.Adapter<AssigmentAdapter.AssignmentViewHolder> {

    private ArrayList<AssignmentItem> data = new ArrayList<>();
    public final AssignmentAdapterListener listener;

    public AssigmentAdapter(AssignmentAdapterListener listener) {
        this.listener = listener;
    }

    public void setData(ArrayList<AssignmentItem> list) {
        this.data = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AssignmentViewHolder(ItemAssignmentBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class AssignmentViewHolder extends RecyclerView.ViewHolder {
        private final ItemAssignmentBinding binding;

        public AssignmentViewHolder(@NonNull ItemAssignmentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(AssignmentItem item) {
            binding.assignmentName.setText(item.getJudul());
            binding.assignmentMaterial.setText(item.getMateri());
            binding.getRoot().setOnClickListener(v -> listener.onItemClick(item));
        }
    }

    public interface AssignmentAdapterListener {
        void onItemClick(AssignmentItem item);
    }
}
