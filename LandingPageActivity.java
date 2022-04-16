package com.cafenoion.grizzlymovie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class LandingPageActivity extends AppCompatActivity {

    private LinearLayout logo;
    private ImageView mark;
    Animation fadeIn;
    Animation fadeOut;
    RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_landing_page );

        //設定隱藏標題
        getSupportActionBar().hide();

        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_FULLSCREEN );

        fadeIn = AnimationUtils.loadAnimation( LandingPageActivity.this, R.anim.fade_in );
        fadeOut = AnimationUtils.loadAnimation( LandingPageActivity.this, R.anim.fade_out );

        logo = findViewById( R.id.logo );
        mark = findViewById( R.id.mark );
        ratingBar = (RatingBar)findViewById( R.id.ratingBar );
        
        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    super.run();

                    logo.setAnimation( fadeIn );

                    for (int i = 0; i < 4; i++) {
                        for (int n = 0; n < 6; n++) {
                            ratingBar.setRating( n );
                            sleep( 150 );
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    logo.setAnimation( fadeOut );
                    startActivity( new Intent( LandingPageActivity.this, MainActivity.class ) );
                    finish();
                }
            }

        };
        timer.start();
    }

}
