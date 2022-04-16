package com.cafenoion.grizzlymovie.activitys.moviehomeorder;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cafenoion.grizzlymovie.R;

public class MovieScheduleActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_ToMovieSeat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_movie_schedule );
        //設定隱藏標題
        getSupportActionBar().hide();

        btn_ToMovieSeat= (Button)findViewById( R.id.btn_ToMovieSeat );
        btn_ToMovieSeat.setOnClickListener( (View.OnClickListener)this );


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btn_ToMovieSeat.getId()) {

            Intent intentLogin = new Intent(MovieScheduleActivity.this , MovieSeatActivity.class );
            startActivity(intentLogin);
            finish();
        }

    }
}
