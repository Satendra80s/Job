package com.example.jobapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookmarkedJobDao {
    @Insert
    void insert(BookmarkedJob job);

    @Query("SELECT * FROM bookmarked_jobs")
    List<BookmarkedJob> getAllBookmarkedJobs();
}

