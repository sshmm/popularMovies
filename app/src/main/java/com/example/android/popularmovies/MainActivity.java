package com.example.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.popularmovies.MoviesAdapter.MoviesAdapterOnClickHandler;
import com.example.android.popularmovies.utils.JsonUtils;
import com.example.android.popularmovies.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MoviesAdapterOnClickHandler {

    private RecyclerView recyclerView;
    private MoviesAdapter moviesAdapter;
    private TextView connectionView;
    private String moviesData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectionView = findViewById(R.id.tv_connection_error);
        recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager
                = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        moviesAdapter = new MoviesAdapter(this, MainActivity.this);

        recyclerView.setAdapter(moviesAdapter);
        if (isOnline()) {
            makeMoviesDbSearchQuery("pop");
        } else {
            recyclerView.setVisibility(View.GONE);
            connectionView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onClick(int position) {
        movieDetailActivity(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void makeMoviesDbSearchQuery(String sortType) {
        URL movieDbSearchUrl = NetworkUtils.buildURL(sortType);

        new MovieDbQueryTask().execute(movieDbSearchUrl);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.popularity_sort) {
            makeMoviesDbSearchQuery("pop");
            return true;
        } else if (itemThatWasClickedId == R.id.voting_sort) {
            makeMoviesDbSearchQuery("vot");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void movieDetailActivity(int position) {
        Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtra(MovieActivity.EXTRA_POSITION, position);
        startActivity(intent);

    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public class MovieDbQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String movieDbQueryResults = "A";
            try {
                movieDbQueryResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
                Log.e("Back", "Help");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return movieDbQueryResults;
        }

        @Override
        protected void onPostExecute(String s) {
            List<String> moviesListResults;


            if (s != null && !s.equals("")) {

                moviesListResults = JsonUtils.getResults(s);
                moviesAdapter.setMoviesData(moviesListResults);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }
    }
}
