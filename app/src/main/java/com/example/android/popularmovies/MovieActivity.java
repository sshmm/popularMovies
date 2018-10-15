package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {
    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private TextView titleView;
    private TextView descriptionView;
    private TextView ratingsView;
    private TextView dateView;
    private ImageView posterView;
    private Movie movie;
    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);


        posterView = findViewById(R.id.movie_poster);
        descriptionView = findViewById(R.id.movie_descritpion);
        ratingsView = findViewById(R.id.movie_rating);
        titleView = findViewById(R.id.movie_title);
        dateView = findViewById(R.id.movie_year);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }
        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            //EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        movie = MoviesAdapter.getMovieData(position);
        imageUrl = "http://image.tmdb.org/t/p/w185/" + movie.getPosterPath();
        populateUI(movie);
        posterView = findViewById(R.id.movie_poster);
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.with(this)
                    .load(imageUrl)
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(posterView);
        }

    }

    public void closeOnError() {
        finish();
        Toast.makeText(this, "Movie data is not available.", Toast.LENGTH_LONG).show();
    }


    private void populateUI(Movie movie) {

        titleView = findViewById(R.id.movie_title);
        titleView.setText(movie.getTitle());

        descriptionView = findViewById(R.id.movie_descritpion);
        descriptionView.setText(movie.getDescription());

        ratingsView = findViewById(R.id.movie_rating);
        ratingsView.setText(movie.getVoteAverage());

        dateView = findViewById(R.id.movie_year);
        dateView.setText(movie.getReleaseDate());

        dateView = findViewById(R.id.movie_year);

    }

}
