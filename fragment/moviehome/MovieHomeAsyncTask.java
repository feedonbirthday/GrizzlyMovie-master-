package com.cafenoion.grizzlymovie.fragment.moviehome;

import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieHomeAsyncTask extends AsyncTask<Void,Void, ArrayList<MovieHomeModel>> {

    private Context context;
    private ArrayList<MovieHomeModel> list;

    private RecyclerView recyclerView;
    private MovieHomeRecyclerAdapter movieHomeItemAdapter;

    public MovieHomeAsyncTask() {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    protected ArrayList<MovieHomeModel> doInBackground(Void... voids) {
        //return this.contact;
        return null;
    }
}
