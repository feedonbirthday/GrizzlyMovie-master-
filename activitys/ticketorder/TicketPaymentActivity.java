package com.cafenoion.grizzlymovie.activitys.ticketorder;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.cafenoion.grizzlymovie.R;

public class TicketPaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_ticket_payment );
        //設定隱藏標題
        getSupportActionBar().hide();
    }
}
