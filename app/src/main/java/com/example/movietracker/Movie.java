package com.example.movietracker;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class Movie {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull public String title;
    @NonNull public String category;

    public Movie(@NonNull String title, @NonNull String category) {
        this.title = title;
        this.category = category;
    }
}
