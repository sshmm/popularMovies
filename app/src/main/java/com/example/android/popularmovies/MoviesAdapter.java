package com.example.android.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.utils.JsonUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {

    private static List<String> sMoviesData = new ArrayList<>();
    private final MoviesAdapterOnClickHandler mClickHandler;
    Context context;
    private List<String> mMoviesData = new ArrayList<>();

    public MoviesAdapter(MoviesAdapterOnClickHandler clickHandler, Context context) {
        mClickHandler = clickHandler;
        this.context = context;
    }

    public static Movie getMovieData(int position) {
        return JsonUtils.parseMovieJson(sMoviesData.get(position));
    }

    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.images_grid;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutId, parent, shouldAttachToParentImmediately);

        return new MoviesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesAdapterViewHolder moviesAdapterViewHolder, int position) {
        Movie movie = JsonUtils.parseMovieJson(mMoviesData.get(position));
        String imageUrl = "http://image.tmdb.org/t/p/w185/" + movie.getPosterPath();
        //moviesAdapterViewHolder.mMovieImageView.getContext()
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.with(context)
                    .load(imageUrl)
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(moviesAdapterViewHolder.mMovieImageView);
        }
    }

    @Override
    public int getItemCount() {
        if (null == mMoviesData) return 0;
        return mMoviesData.size();
    }

    public void setMoviesData(List<String> moviesData) {
        mMoviesData = moviesData;
        sMoviesData = moviesData;
        notifyDataSetChanged();
    }


    /*
    The interface that receives onClick Messages;
     */
    public interface MoviesAdapterOnClickHandler {
        void onClick(int position);
    }

    /*
    View Holder for the image view
     */
    public class MoviesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final ImageView mMovieImageView;


        public MoviesAdapterViewHolder(View view) {
            super(view);
            mMovieImageView = view.findViewById(R.id.movie_thumbnail);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.onClick(adapterPosition);
        }
    }

//    private int dpToPx(int dp)
//    {
//        float density = context.getContext().getResources().getDisplayMetrics().density;
//        return Math.round((float)dp * density);
//    }
}
