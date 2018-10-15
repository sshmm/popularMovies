package com.example.android.popularmovies.model;

public class Movie {
    private String title;
    private String releaseDate;
    private String posterPath;
    private String voteAverage;
    private String description;


    public Movie() {

    }

    public Movie(String title, String releaseDate, String posterPath, String voteAverage,
                 String description) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

/*

poster_path
string or null
optional
adult
boolean
optional
overview
string
optional
release_date
string
optional
genre_ids
array[integer]
optional
id
integer
optional
original_title
string
optional
original_language
string
optional
title
string
optional
backdrop_path
string or null
optional
popularity
number
optional
vote_count
integer
optional
video
boolean
optional
vote_average
number
optional
total_results
integer
optional
total_pages
integer
optional
collapse

 */