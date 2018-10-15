package com.example.android.popularmovies.utils;

import com.example.android.popularmovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static List<String> getResults(String movies) {
        try {

            JSONObject json = new JSONObject(movies);
            JSONArray resultsArray = json.getJSONArray("results");
            List<String> result = new ArrayList<String>();
            for (int i = 0; i < resultsArray.length(); i++) {
                result.add(resultsArray.getString(i));
            }

            return result;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Movie parseMovieJson(String movieData) {
        try {
            JSONObject movie = new JSONObject(movieData);
            String title = movie.getString("title");
            double dVoteAverage = movie.getDouble("vote_average");
            String voteAverage = Double.toString(dVoteAverage);
            String posterPath = movie.getString("poster_path");
            String releaseDate = movie.getString("release_date");
            String description = movie.getString("overview");

            return new Movie(title, releaseDate, posterPath, voteAverage, description);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
