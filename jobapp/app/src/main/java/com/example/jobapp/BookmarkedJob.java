package com.example.jobapp;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookmarked_jobs")
public class BookmarkedJob {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String location;
    public String salary;
    public String phone;
}
