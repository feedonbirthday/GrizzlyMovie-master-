package com.cafenoion.grizzlymovie.activitys.moviehomeorder;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cafenoion.grizzlymovie.MainActivity;
import com.cafenoion.grizzlymovie.R;
import com.cafenoion.grizzlymovie.activitys.member.UsersLoginActivity;

public class MovieSeatActivity extends AppCompatActivity  implements View.OnClickListener{
    private Button btn_ToMovieTicketFragment;
    private ImageView imageView;

    String movie_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_movie_seat );

        //設定隱藏標題
        getSupportActionBar().hide();

        //測試----------------
        //TODO
        movie_id = "1";

        ImageView imageView = (ImageView) findViewById(R.id.imageView1);
        imageView.setColorFilter( Color.rgb(239,105,105  ));

        //imageView.setOnClickListener( (View.OnClickListener)this );

        imageView = (ImageView) findViewById(R.id.imageView2);
        imageView.setColorFilter(Color.rgb(50,50,36  ));

        imageView = (ImageView) findViewById(R.id.imageView3);
        imageView.setColorFilter(Color.rgb(50,50,36  ));
        //測試----------------


        //
        RelativeLayout rLayout = (RelativeLayout) findViewById(R.id.relativeLayout);


        RelativeLayout.LayoutParams lprams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        ImageView tv1 = new ImageView(this);
        tv1.setLayoutParams(lprams);
        rLayout.addView(tv1);

        

        btn_ToMovieTicketFragment= (Button)findViewById( R.id.btn_ToMovieTicketFragment );
        //btn_ToMovieTicketFragment.setOnClickListener( (View.OnClickListener)this );
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btn_ToMovieTicketFragment.getId()) {
            //傳值給 電影票
            //TODO
//            Intent intent= new Intent(MovieSeatActivity.this, MainActivity.class);
//            intent.putExtra("fragment_id",1);
//            intent.putExtra("movie_id", movie_id);
//            startActivity(intent);
            finish();


        }
        if(v.getId() == imageView.getId()){

            //imageView.setColorFilter( Color.rgb(239,105,105  ));
            //text.setTextColor(0xFF008080);
        }
    }
}
