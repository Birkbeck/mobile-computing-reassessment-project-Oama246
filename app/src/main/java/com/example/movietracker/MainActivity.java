package com.example.movietracker;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MovieAdapter(movie -> {
            Intent i = new Intent(MainActivity.this, EditMovieActivity.class);
            i.putExtra("id", movie.id);
            i.putExtra("title", movie.title);
            i.putExtra("category", movie.category);
            startActivity(i);
        });
        rv.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(v -> startActivity(new Intent(this, AddMovieActivity.class)));

        MovieDatabase.getInstance(this).movieDao().getAllMovies().observe(this, movies -> adapter.setMovies(movies));
    }
}
