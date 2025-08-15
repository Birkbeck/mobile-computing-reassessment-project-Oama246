package com.example.movietracker;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddMovieActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        EditText editTitle = findViewById(R.id.editTitle);
        Spinner spinner = findViewById(R.id.spinnerCategory);
        Button btnSave = findViewById(R.id.buttonSave);

        String[] categories = getResources().getStringArray(R.array.movie_categories);
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories));

        btnSave.setOnClickListener(v -> {
            String title = editTitle.getText().toString().trim();
            String category = spinner.getSelectedItem().toString();
            if (TextUtils.isEmpty(title)) {
                Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show();
                return;
            }
            MovieDatabase.getInstance(this).movieDao().insert(new Movie(title, category));
            finish();
        });
    }
}
