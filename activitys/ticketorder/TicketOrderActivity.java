package com.cafenoion.grizzlymovie.activitys.ticketorder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cafenoion.grizzlymovie.R;

public class TicketOrderActivity extends AppCompatActivity {

    Intent intent;
    Bundle bag;
    Button btn_previous;
    TextView list_View;
    int MovieImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_ticket_order );

        //設定隱藏標題
        getSupportActionBar().hide();


        //使用bundle接收資料+setText
        bag = getIntent().getExtras();
//        MovieImg = bag.getInt("movie_img");
//        ImageView Pic2 = findViewById(R.id.Pic2);
//        Pic2.setImageResource(MovieImg);
//        TextView tvTxtName = findViewById(R.id.TxtName);
//        tvTxtName.setText(bag.getString("movie_name"));
//        TextView tvDate = findViewById(R.id.TxtDate);
//        tvDate.setText(bag.getString("movie_date"));
//        TextView tvIntro = findViewById(R.id.TxtIntro);
//        tvIntro.setText(bag.getString("movie_intro"));
    }
}
