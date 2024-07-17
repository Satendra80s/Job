package com.example.jobapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobapp.AppDatabase;
import com.example.jobapp.BookmarkedJob;
import com.example.jobapp.JobApi;
import com.example.jobapp.databinding.FragmentHomeBinding;

import java.util.List;

import kotlinx.coroutines.Job;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    public void bookmarkJob(Job job) {
        BookmarkedJob bookmarkedJob = new BookmarkedJob();
        bookmarkedJob.title = job.getTitle();
        bookmarkedJob.location = job.getLocation();
        bookmarkedJob.salary = job.getSalary();
        bookmarkedJob.phone = job.getPhone();

        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(getContext());
            db.bookmarkedJobDao().insert(bookmarkedJob);
        }).start();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            if (!recyclerView.canScrollVertically(1)) {
                currentPage++;
                fetchJobs(currentPage);
            }
        }
    });



    private void fetchJobs(int page) {
        JobApi jobApi = new Retrofit.Builder()
                .baseUrl("https://testapi.getlokalapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(JobApi.class);

        jobApi.getJobs(page).enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    jobList.addAll(response.body());
                    jobAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                // Handle error
            }
        });
    }

}
