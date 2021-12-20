package com.patriciadevitasamara.uas_pyb.activity.course;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.patriciadevitasamara.uas_pyb.databinding.ItemCourseBinding;
import com.patriciadevitasamara.uas_pyb.model.course.CourseItem;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private ArrayList<CourseItem> data = new ArrayList<>();
    public final CourseAdapterListener listener;

    public CourseAdapter(CourseAdapterListener listener) {
        this.listener = listener;
    }

    public void setData(ArrayList<CourseItem> list) {
        this.data = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CourseViewHolder(ItemCourseBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        private final ItemCourseBinding binding;

        public  CourseViewHolder(@NonNull ItemCourseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(CourseItem item) {
            binding.textViewCourseName.setText(item.getNamaKelas());
            binding.textViewCourseLesson.setText(item.getMataPelajaran());
            binding.textViewCourseCapacity.setText(item.getKapasitas());
            binding.textViewCourseCode.setText(item.getKode());
            binding.getRoot().setOnClickListener(v -> listener.onItemClick(item));
        }
    }

    public interface CourseAdapterListener {
        void onItemClick(CourseItem item);
    }
}
