package com.cafenoion.grizzlymovie.fragment.moviehome;


import android.content.ClipData;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.cafenoion.grizzlymovie.MethodClass;
import com.cafenoion.grizzlymovie.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MovieHomeFragment extends Fragment  {

    View root;

    private  String URL1 = null;
    private  String URL2 = null;
    private  String URL3 = null;

    private RecyclerView tRecyclerView;
    public MovieHomeRecyclerAdapter tListAdapter = null;
    public ArrayList<MovieHomeModel> tItem = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root =   inflater.inflate( R.layout.fragment_movie_home, container, false);

//        URL1 = "https://clvsc41118101.000webhostapp.com/App/json0.php?Screenings=1";
//        URL2 = "https://clvsc41118101.000webhostapp.com/App/json0.php?Screenings=2";
//        URL3 = "https://clvsc41118101.000webhostapp.com/App/json0.php?Screenings=3";


        for(int i = 1 ; i<= 3 ; i++){
            switch (i){
                case 1:
                    tRecyclerView = root.findViewById(R.id.in_Theatres);
                    setMovieItem(URL1 , i);
                    break;
                case 2:
                    tRecyclerView = root.findViewById(R.id.new_Trailer);
                    setMovieItem(URL2 , i);
                    break;
                case 3:
                    tRecyclerView = root.findViewById(R.id.coming_Soon);
                    setMovieItem(URL3 , i);
                    break;
            }


            tListAdapter = new MovieHomeRecyclerAdapter( getActivity(),tItem );
            tRecyclerView.setAdapter(tListAdapter);
            final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            tRecyclerView.setLayoutManager(layoutManager);
            tRecyclerView.setAdapter(tListAdapter);
            tRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


        }
        return root;
    }

    private void setMovieItem(String uRL , int i) {

        //當  Json 資料為空值
        if(uRL == null)
        {
            tItem = new ArrayList<>();
            switch (i)
            {
                case  1:
                    tItem.add(new MovieHomeModel("6",R.drawable.m06,"變形金剛5",3));
                    tItem.add(new MovieHomeModel("13",R.drawable.m13,"小丑",4));
                    tItem.add(new MovieHomeModel("14",R.drawable.m14,"雙子殺手",4));
                    tItem.add(new MovieHomeModel("19",R.drawable.m19,"玩命關頭8",4));
                    break;
                case 2:
                    tItem.add(new MovieHomeModel("5",R.drawable.m05,"為了與你相遇",4));
                    tItem.add(new MovieHomeModel("16",R.drawable.m16,"會計師",4));
                    tItem.add(new MovieHomeModel("17",R.drawable.m17,"型男飛行日誌",4));
                    tItem.add(new MovieHomeModel("18",R.drawable.m18,"功夫熊貓3",4));
                    break;
                case 3:
                    tItem.add(new MovieHomeModel("7",R.drawable.m07,"氣象戰",4));
                    tItem.add(new MovieHomeModel("10",R.drawable.m10,"美國狙擊手",4));
                    tItem.add(new MovieHomeModel("11",R.drawable.m11,"美國隊長",4));
                    tItem.add(new MovieHomeModel("20",R.drawable.m20,"絕命終結站5",4));
                    break;
            }
        }

        else {
            MovieHomeAsyncTask process;
            process = new MovieHomeAsyncTask();
        }
    }


//    public class JasonAsyncTask extends AsyncTask<Void,Void,Void> {
//    String data ="";
//    String dataParsed = "";
//    String singleParsed ="";
//
//
//        String strUrl;
//
//        public JasonAsyncTask(String strUrl) {
//            this.strUrl = strUrl;
//            doInBackground();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            //super.tItem = new ArrayList<>();
//            String line = "";
//            try {
//                URL url = new URL(strUrl);
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                InputStream inputStream = httpURLConnection.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//
//                while(line != null){
//                    line = bufferedReader.readLine();
//                    data = data + line;
//                }
//                JSONArray JA = new JSONArray(data);
//
//
//
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//        }
//    }

}
