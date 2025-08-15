package com.example.movietracker;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditMovieActivity extends AppCompatActivity {
    private int movieId;
    private String initialTitle = "";
    private String initialCategory = "Other";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        EditText editTitle = findViewById(R.id.editTitle);
        Spinner spinner = findViewById(R.id.spinnerCategory);
        Button btnUpdate = findViewById(R.id.buttonUpdate);
        Button btnDelete = findViewById(R.id.buttonDelete);

        String[] categories = getResources().getStringArray(R.array.movie_categories);
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories));

        movieId = getIntent().getIntExtra("id", -1);
        String incomingTitle = getIntent().getStringExtra("title");
        String incomingCategory = getIntent().getStringExtra("category");

        if (incomingTitle != null) { initialTitle = incomingTitle; editTitle.setText(incomingTitle); }
        if (incomingCategory != null) { initialCategory = incomingCategory; }

        int index = 0;
        for (int i = 0; i < categories.length; i++) {
            if (categories[i].equals(initialCategory)) { index = i; break; }
        }
        spinner.setSelection(index);

        btnUpdate.setOnClickListener(v -> {
            String title = editTitle.getText().toString().trim();
            String category = spinner.getSelectedItem().toString();
            if (movieId == -1) { finish(); return; }
            if (TextUtils.isEmpty(title)) {
                Toast.makeText(this, "Title required", Toast.LENGTH_SHORT).show();
                return;
            }
            Movie movie = new Movie(title, category);
            movie.id = movieId;
            MovieDatabase.getInstance(this).movieDao().update(movie);
            finish();
        });

        btnDelete.setOnClickListener(v -> {
            if (movieId == -1) { finish(); return; }
            Movie movie = new Movie(initialTitle, initialCategory);
            movie.id = movieId;
            MovieDatabase.getInstance(this).movieDao().delete(movie);
            finish();
        });
    }
}
