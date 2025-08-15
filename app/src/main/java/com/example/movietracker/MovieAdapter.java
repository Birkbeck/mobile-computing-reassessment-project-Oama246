package com.example.movietracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    public interface OnMovieClickListener { void onMovieClick(Movie movie); }
    private final OnMovieClickListener listener;
    private List<Movie> movies = new ArrayList<>();

    public MovieAdapter(OnMovieClickListener listener) { this.listener = listener; }
    public void setMovies(List<Movie> movies) { this.movies = movies; notifyDataSetChanged(); }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie m = movies.get(position);
        holder.title.setText(m.title);
        holder.category.setText(m.category);
        holder.itemView.setOnClickListener(v -> { if (listener != null) listener.onMovieClick(m); });
    }

    @Override
    public int getItemCount() { return movies.size(); }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title, category;
        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movieTitle);
            category = itemView.findViewById(R.id.movieCategory);
        }
    }
}
