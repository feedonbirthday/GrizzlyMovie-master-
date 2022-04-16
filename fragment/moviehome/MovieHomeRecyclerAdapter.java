package com.cafenoion.grizzlymovie.fragment.moviehome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.cafenoion.grizzlymovie.R;
import com.cafenoion.grizzlymovie.activitys.moviehomeorder.MoveYouTubeActivity;

import java.util.ArrayList;

public class MovieHomeRecyclerAdapter extends RecyclerView.Adapter<MovieHomeRecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<MovieHomeModel> movies;

    class ViewHolder extends RecyclerView.ViewHolder {

        int position;
        TextView movie_id;
        ImageView movie_poster;
        TextView movie_name;
        RatingBar ratingBar;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            movie_id =  itemView.findViewById( R.id.movie_id);
            movie_poster = itemView.findViewById( R.id.movie_poster);
            movie_name = itemView.findViewById(R.id.movie_name);
            ratingBar = itemView.findViewById(R.id.ratingBar);

            movie_poster.setOnClickListener(new View.OnClickListener(){
               @Override
                public void onClick(View v) {
                Intent intent = new Intent();
                Bundle ticket = new Bundle();
                ticket.putString("movie_id", movie_id.getText().toString());
                intent.putExtras(ticket);
                intent.setClass(v.getContext(), MoveYouTubeActivity.class);
                context.startActivity(intent);
                }
            });
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_moviehome_recycler_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public MovieHomeRecyclerAdapter(Context context, ArrayList<MovieHomeModel>movies){
        this.context = context;
        this.movies = movies;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MovieHomeModel item = movies.get(position);
        holder.movie_id.setText(item.getMovie_id());
        holder.movie_poster.setImageResource(item.getMovie_poster());
        holder.movie_name.setText(item.getMovie_name());
        holder.ratingBar.setRating(item.getRating());
        holder.position = position;
    }
}
