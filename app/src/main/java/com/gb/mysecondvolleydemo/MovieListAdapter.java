package com.gb.mysecondvolleydemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by bhavin on 8/4/2018.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private Context context;

    public MovieListAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_row,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        Glide.with(context).load(movies.get(position).getImageUrl()).skipMemoryCache(true).into(holder.ivMovie);

        holder.tvTitle.setText(movies.get(position).getTitle());
        holder.tvRating.setText("Rating: "+String.valueOf(movies.get(position).getRating()));
        holder.tvYear.setText(String.valueOf(movies.get(position).getYear()));

        String genreStr = "";
        for (String str : movies.get(position).getGenre()){
            genreStr += str +", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,genreStr.length() - 2) : genreStr;
        holder.tvGenre.setText(genreStr);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView ivMovie;
        TextView tvTitle,tvRating,tvYear,tvGenre;

        public MovieViewHolder(View itemView) {
            super(itemView);

            ivMovie = (ImageView) itemView.findViewById(R.id.iv_movie);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvRating = (TextView) itemView.findViewById(R.id.tv_rating);
            tvGenre = (TextView) itemView.findViewById(R.id.tv_genre);
            tvYear = (TextView) itemView.findViewById(R.id.tv_releaseYear);

        }
    }
}
