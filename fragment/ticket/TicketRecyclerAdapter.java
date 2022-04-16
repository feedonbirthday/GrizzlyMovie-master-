package com.cafenoion.grizzlymovie.fragment.ticket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cafenoion.grizzlymovie.R;
import com.cafenoion.grizzlymovie.activitys.ticketorder.TicketOrderActivity;
import java.util.ArrayList;

public class TicketRecyclerAdapter extends RecyclerView.Adapter<TicketRecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TicketModel> movies;

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_name,txt_Intro,txt_date;
        int position;
        ImageView ticket_image;
        Button but_sub,but_story;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            ticket_image = itemView.findViewById(R.id.ticket_image);
            txt_name = itemView.findViewById(R.id.TxtName);
            txt_Intro = itemView.findViewById(R.id.TxtIntro);
            txt_date = itemView.findViewById(R.id.TxtDate);

            ticket_image.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                Intent intent = new Intent();
                Bundle ticket = new Bundle();
                ticket.putInt("movie_img", movies.get(position).getMovieImg());
                ticket.putString("movie_name", txt_name.getText().toString());
                ticket.putString("movie_date", txt_date.getText().toString());
                ticket.putString("movie_intro", txt_Intro.getText().toString());
                intent.putExtras(ticket);
                intent.setClass(v.getContext(), TicketOrderActivity.class);
                context.startActivity(intent);
                }
            });
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ticker_recycler_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ticket_image.setImageResource(movies.get(position).getMovieImg());
        holder.txt_name.setText(movies.get(position).getMovieName());
        holder.txt_Intro.setText(movies.get(position).getMovieIntro());
        holder.txt_date.setText(movies.get(position).getMovieDate());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public TicketRecyclerAdapter(Context context, ArrayList<TicketModel>movies){
        this.context = context;
        this.movies = movies;
    }
}
