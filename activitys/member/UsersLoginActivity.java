package com.cafenoion.grizzlymovie.activitys.member;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.cafenoion.grizzlymovie.MainActivity;
import com.cafenoion.grizzlymovie.activitys.moviehomeorder.MovieSeatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.cafenoion.grizzlymovie.R;

public class UsersLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = UsersLoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private Button btnLogin;

    private TextView textLinkRegister;

    private UsersInputValidation inputValidation;
    private UsersDataHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_login );

        //設定隱藏標題
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * 初始化物件
     */
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById( R.id.nestedScrollView);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        btnLogin = (Button) findViewById(R.id.buttonLogin);

        textLinkRegister = (TextView) findViewById(R.id.textLnkRegister);

    }

    /**
     * 初始化物件
     */
    private void initListeners() {
        btnLogin.setOnClickListener(this);
        textLinkRegister.setOnClickListener(this);
    }

    /**
     * 初始化物件
     */
    private void initObjects() {
        databaseHelper = new UsersDataHelper(activity);
        inputValidation = new UsersInputValidation(activity);

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                verifyFromSQLite();
                break;
            case R.id.textLnkRegister:
                Intent intentRegister = new Intent(getApplicationContext(), UsersRegisterActivity.class);
                startActivity(intentRegister);
                finish();
                break;
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {

        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
         else if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return;
        }


        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {

            Intent intent= new Intent(UsersLoginActivity.this, MainActivity.class);
            intent.putExtra("fragment_id",3);
            intent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            startActivity(intent);
            finish();


            //Intent accountsIntent = new Intent(activity, UsersListActivity.class);
            //accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            //startActivity(accountsIntent);
            emptyInputEditText();

            finish();


        } else {
            // 底部錯誤訊息 Snackbar

            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_INDEFINITE)
                    .setAction("建立我的帳號", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //設計點擊訊息時的工作
                            Intent intentRegister = new Intent(getApplicationContext(), UsersListActivity.class);
                            startActivity(intentRegister);
                            finish();
                        }
                    })
                    .setActionTextColor(Color.rgb( 255, 194, 14))
                    .show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
