package com.example.jobapp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import kotlinx.coroutines.Job;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {
    private List<Job> jobList;

    public JobAdapter(List<Job> jobList) {
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_item, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Job job = jobList.get(position);
        holder.title.setText(job.getTitle());
        holder.location.setText(job.getLocation());
        holder.salary.setText(job.getSalary());
        holder.phone.setText(job.getPhone());

        holder.itemView.setOnClickListener(v -> {
            // Handle job card click
        });
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {
        TextView title, location, salary, phone;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.job_title);
            location = itemView.findViewById(R.id.job_location);
            salary = itemView.findViewById(R.id.job_salary);
            phone = itemView.findViewById(R.id.job_phone);
        }
    }
}

