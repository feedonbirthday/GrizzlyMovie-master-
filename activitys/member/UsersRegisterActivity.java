package com.cafenoion.grizzlymovie.activitys.member;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.cafenoion.grizzlymovie.R;


/**
 * Created by Michael on 2019/11/16.
 */
public class UsersRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = UsersRegisterActivity.this;
//
    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPhone;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPhone;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private Button btnRegister;
    private TextView textViewLoginLink;

    private UsersInputValidation inputValidation;
    private UsersDataHelper databaseHelper;
    private UsersModel user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_users_register );

        //設定隱藏標題
        getSupportActionBar().hide();


        initViews();
        initListeners();
        initObjects();
    }


    private void initViews() {
       nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPhone = (TextInputLayout) findViewById(R.id.textInputLayoutPhone);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPhone = (TextInputEditText) findViewById(R.id.textInputEditTextPhone);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);

        btnRegister = (Button) findViewById(R.id.buttonRegister);
        textViewLoginLink = (TextView) findViewById(R.id.textLoginLink);
    }


    private void initListeners() {
        btnRegister.setOnClickListener(this);
        textViewLoginLink.setOnClickListener(this);
    }


    private void initObjects() {
       inputValidation = new UsersInputValidation(activity);
       databaseHelper = new UsersDataHelper(activity);
       user = new UsersModel();

    }


    //
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonRegister:
                postDataToSQLite();

                break;

            case R.id.textLoginLink:
                Intent intentLoginB = new Intent(getApplicationContext(), UsersLoginActivity.class);
                startActivity(intentLoginB);
                finish();
                break;
        }
    }

    // 寫入 SQLite
    private void postDataToSQLite() {


        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        else if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        else if (!inputValidation.isInputEditTextFilled(textInputEditTextPhone, textInputLayoutPhone, getString(R.string.error_message_Phone))) {
            return;
        }
        else if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        else if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        else {
            if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {

                user.setName(textInputEditTextName.getText().toString().trim());
                user.setEmail(textInputEditTextEmail.getText().toString().trim());
                user.setPhone(textInputEditTextEmail.getText().toString().trim());
                user.setPassword(textInputEditTextPassword.getText().toString().trim());

                databaseHelper.addUser(user);

                // Snack Bar to show success message that record saved successfully
                Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
                emptyInputEditText();


            } else {
                // Snack Bar to show error message that record already exists
                Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
            }

            Intent intentLoginA = new Intent(getApplicationContext(), UsersLoginActivity.class);
            startActivity(intentLoginA);
            finish();
            
        }


    }

    // 清空資料
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPhone.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }
}
