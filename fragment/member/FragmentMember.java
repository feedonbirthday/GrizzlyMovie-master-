package com.cafenoion.grizzlymovie.fragment.member;

import com.cafenoion.grizzlymovie.activitys.member.UsersRecyclerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.cafenoion.grizzlymovie.MethodClass;
import com.cafenoion.grizzlymovie.R;
import com.cafenoion.grizzlymovie.activitys.member.UsersRegisterActivity;
import com.cafenoion.grizzlymovie.activitys.member.UsersListActivity;
import com.squareup.picasso.Picasso;


public class FragmentMember extends Fragment implements View.OnClickListener {

    View root;
    private Button btn_SetUp;
    RatingBar ratingBar;

    TextView textAccountMessage;

    TextView textMessage;

    ImageView profile_image;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root =inflater.inflate(R.layout.fragment_member, container, false);

        //星星設定
        ratingBar = (RatingBar) root.findViewById(R.id.ratingBar);
        textAccountMessage = root.findViewById(R.id.textAccountMessage);
        textMessage = root.findViewById(R.id.textMessage);

        //星星設定
        ratingBar.setRating(5);
        float rating = ratingBar.getRating();
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            }
        });


        profile_image = root.findViewById( R.id.profile_image );
        profile_image.setOnClickListener(this);
        btn_SetUp = root.findViewById( R.id.btn_SetUp );
        btn_SetUp.setOnClickListener(this);
        textAccountMessage = root.findViewById( R.id.textAccountMessage );
        textAccountMessage.setOnClickListener(this);

        try {
            Bundle arguments = getArguments();

            String data = arguments.getString("KEY");
            //TODO 驗證 EMILL
            textAccountMessage.setText(data);
            textMessage.setText("會員尊榮.........");

            Picasso.get()
                    .load(R.drawable.a001)
                    .transform(new MethodClass.CircleTransform())
                    .into(profile_image);

            btn_SetUp.setVisibility(View.INVISIBLE);


            Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Picasso.get()
                    .load(R.drawable.moon)
                    .transform(new MethodClass.CircleTransform())
                    //.transform(new MethodClass.RoundedTransformation(20, 0))
                    .into(profile_image);

        }

        //暫時顯示






        return root;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btn_SetUp.getId()) {

            Intent intentLogin = new Intent(getActivity() , UsersRegisterActivity.class );
            startActivity(intentLogin);
        }
        if(v.getId() == textAccountMessage.getId()) {

            Intent intentLogin = new Intent(getActivity() , UsersListActivity.class );
            startActivity(intentLogin);
        }
        
    }

}
