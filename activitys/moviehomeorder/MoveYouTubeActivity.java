package com.cafenoion.grizzlymovie.activitys.moviehomeorder;

//import androidx.appcompat.app.AppCompatActivity;
import com.cafenoion.grizzlymovie.activitys.member.UsersDataHelper;
import com.cafenoion.grizzlymovie.activitys.member.UsersInputValidation;
import com.cafenoion.grizzlymovie.config.NetConfig;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.cafenoion.grizzlymovie.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.cafenoion.grizzlymovie.MethodClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static com.cafenoion.grizzlymovie.config.NetConfig.MOVIE_POSTER;
import static com.cafenoion.grizzlymovie.config.NetConfig.MOVIE_URL;


public class MoveYouTubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, View.OnClickListener {
    public  static  final  String API_KEY = "YOUR API KEY";
    String NIDEO_ID ;//= "XF8h3hOGBJM";// = "Eoook2Ee6q0";
    String movie_id;
    private Button btn_ToMovieSchedule;

    YouTubePlayerView youTubePlayerView;

    ImageView imagePoster;
    ImageView imageRat;
    TextView textGenre;
    TextView textTitle;
    TextView textDirector;
    TextView textSynopsis;
    RatingBar ratingBar;

    ImageView imageActro;
    TextView textActro;
    TextView textRoles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_move_youtube );

        initViews();
        initListeners();

        // 從 MovieHomeFragment 傳入 電影ID
        Intent intent = getIntent();
        movie_id = intent.getStringExtra("movie_id");

        // 以電影ID 設定顯示 電影資料
        setMovieDate(movie_id);
        // 以電影ID 設定顯示 演員影資料
        setMovieActro(movie_id);
    }

    /**
     * 初始化物件
     */
    private void initViews() {

        btn_ToMovieSchedule= (Button)findViewById( R.id.btn_ToMovieSchedule );

        imagePoster  = findViewById( R.id.imagePoster );
        imageRat  = findViewById( R.id.imageRat );
        textGenre    = findViewById( R.id.textGenre );
        textTitle    = findViewById( R.id.textTitle );
        textDirector = findViewById( R.id.textDirector );
        textSynopsis = findViewById( R.id.textSynopsis );
        ratingBar    = findViewById( R.id.ratingBar );

        imageActro   = findViewById( R.id.imageActro );
        textActro    = findViewById( R.id.textActro );
        textRoles    = findViewById( R.id.textRoles );

    }

    /**
     * 初始化物件
     */
    private void initListeners() {
        btn_ToMovieSchedule.setOnClickListener( this );
    }


    @Override
    public void onClick(View v) {
        Intent intentLogin = new Intent(MoveYouTubeActivity.this , MovieScheduleActivity.class );
        startActivity(intentLogin);
        finish();
    }

    public void setMovieActro(final String movie_ID){

        // TODO 改寫
        // 演員
        Picasso.get()
                .load(R.drawable.a001)
                .transform(new MethodClass.RoundedTransformation(20, 0))
                .into(imageActro);

    }
    public void setMovieDate(final String movie_ID){
        new Thread() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL(MOVIE_URL+movie_ID);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

                    // StringBuilder，繼續添加字符串的位
                    StringBuilder builder = new StringBuilder();
                    String response;
                    while ((response = bufferedReader.readLine()) != null) {
                        builder.append(response);
                    }

                    //需要使用Handler
                    Message msg = Message.obtain();
                    //提供從StringBuilder對象創建的字符串
                    msg.obj = builder.toString();
                    movieHandler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    Handler movieHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            try {
                //此處接收到的字符串是JSON字符串陣列，轉換為JSONObject
                // 單筆電影資料
                JSONArray jSONArray = new JSONArray(msg.obj.toString());
                JSONObject jSONObject = (JSONObject) jSONArray.get(0);

                String poster    = jSONObject.getString("Poster");
                String title_c   = jSONObject.getString("Title_c");
                String director  = "導演："+jSONObject.getString("Director");
                String synopsis  = jSONObject.getString("Synopsis");
                String rating    = jSONObject.getString("Rating");
                String genre     = jSONObject.getString("Genre");
                String ratingbar = jSONObject.getString("Star");

                // 電影片名
                textTitle.setText(title_c);
                // 電影分類
                textGenre.setText(genre);
                // 電影導演
                textDirector.setText(director);
                // 電影簡介
                textSynopsis.setText(synopsis);
                // 電影評分
                ratingBar.setRating( Integer.parseInt(ratingbar) );
                // 電影分級
                setImageRating( Integer.parseInt(rating));
                // 電影海報 imagePoster
                Picasso.get().load(MOVIE_POSTER +poster).transform(new MethodClass.RoundedTransformation(20, 0)).into(imagePoster);
                // 電影預告 Youtube
                NIDEO_ID = jSONObject.getString("Youtube");
                youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubr_play);
                youTubePlayerView.initialize(API_KEY, MoveYouTubeActivity.this);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }
    });
    public void setImageRating(int rating){
        switch (rating){
            case 0 :
                Picasso.get().load(R.drawable.r0) .into(imageRat);
                break;
            case 6 :
                Picasso.get().load(R.drawable.r6) .into(imageRat);
                break;
            case 12:
                Picasso.get().load(R.drawable.r12) .into(imageRat);
                break;
            case 15:
                Picasso.get().load(R.drawable.r15) .into(imageRat);
                break;
            case 18:
                Picasso.get().load(R.drawable.r18) .into(imageRat);
                break;
            default:
                Picasso.get().load(R.drawable.r0) .into(imageRat);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        if (youTubeInitializationResult.isUserRecoverableError()) {

            //youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();

        } else {

            String errorMessage = String.format("There was an error initializing the YouTubePlayer (%1$s)", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }
    YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onBuffering(boolean arg0) {
        }
        @Override
        public void onPaused() {
        }
        @Override
        public void onPlaying() {
        }
        @Override
        public void onSeekTo(int arg0) {
        }
        @Override
        public void onStopped() {
        }
    };
    YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() { }
        @Override
        public void onLoaded(String s) { }

        @Override
        public void onAdStarted() { }

        @Override
        public void onVideoStarted() { }

        @Override
        public void onVideoEnded() { }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) { }

    };
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        //youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        //youTubePlayer.setPlaybackEventListener(playbackEventListener);

        if (!wasRestored) {
            youTubePlayer.cueVideo(NIDEO_ID);
        }
    }
}




