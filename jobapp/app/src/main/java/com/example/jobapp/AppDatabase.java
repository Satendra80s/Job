package com.example.jobapp;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {BookmarkedJob.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BookmarkedJobDao bookmarkedJobDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "job_database").build();
        }
        return instance;
    }
}
