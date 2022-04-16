package com.cafenoion.grizzlymovie;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;
import  com.cafenoion.grizzlymovie.fragment.member.*;
import com.cafenoion.grizzlymovie.activitys.member.UsersLoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 設定隱藏標題
        getSupportActionBar().hide();

        //------------------------------------------------------------------------------------------
        // 載入導航欄 - 由編譯器自動生成
        // 在 mobile_navigation.xml 中設定初始 = navigation_home
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_member).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        //------------------------------------------------------------------------------------------

        // 導航欄切換
        // 可從 Activity 切換到 Fragment 任一頁面
        int id = getIntent().getIntExtra("fragment_id", 0);

        Bundle bundle;
        switch (id){
            case 0:
                //回院線片頁
                navController.navigate(R.id.navigation_home);
                break;
            case 1:
                //回電影票頁

//                String movie_id = getIntent().getStringExtra("movie_id");
//                bundle = new Bundle();
//                bundle.putString("KEY", movie_id);
//                navController.navigate(R.id.navigation_dashboard, bundle);
                navController.navigate(R.id.navigation_home);

                break;
            case 2:
                //回通知頁
                navController.navigate(R.id.navigation_notifications);
                break;
            case 3:
                //回會員頁
                String EMAIL = getIntent().getStringExtra("EMAIL");
                bundle = new Bundle();
                bundle.putString("KEY", EMAIL);
                navController.navigate(R.id.navigation_member, bundle);
                break;
        }
    }
}
