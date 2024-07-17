package com.example.jobapp;
import java.util.List;

import kotlinx.coroutines.Job;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

    public interface JobApi {
        @GET("common/jobs")
        Call<List<Job>> getJobs(@Query("page") int page);
    }


