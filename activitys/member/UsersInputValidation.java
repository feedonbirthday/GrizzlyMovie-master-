package com.cafenoion.grizzlymovie.activitys.member;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

//輸入判斷

public class UsersInputValidation {
    private Context context;

    /**
     * constructor
     *
     * @param context
     */
    public UsersInputValidation(Context context) {
        this.context = context;
    }

    /**
     * 判斷是否是空值
     *
     * @param textInputEditText
     * @param textInputLayout
     * @param message
     * @return
     */
    public boolean isInputEditTextFilled(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty()) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }


    /**
     * 判斷是否是正確的email
     *
     * @param textInputEditText
     * @param textInputLayout
     * @param message
     * @return
     */
    public boolean isInputEditTextEmail(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }
    /**
     * 判斷是否是正確的email
     *
     * @param textInputEditText1  textInputEditText2
     * @param textInputLayout
     * @param message
     * @return
     */

    public boolean isInputEditTextMatches(TextInputEditText textInputEditText1, TextInputEditText textInputEditText2, TextInputLayout textInputLayout, String message) {
        String value1 = textInputEditText1.getText().toString().trim();
        String value2 = textInputEditText2.getText().toString().trim();
        if (!value1.contentEquals(value2)) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText2);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }
    /**
     * 根據區號判斷是否是正確的電話號碼
     * @param phoneNumber :帶國家碼的電話號碼
     * @param countryCode :預設國家碼
     * return ：true 合法  false：不合法
     */
//    public static boolean isPhoneNumberValid(String phoneNumber, String countryCode){
//
//        System.out.println("isPhoneNumberValid: "+phoneNumber+"/"+countryCode);
//        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
//        try{
//            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(phoneNumber, countryCode);
//            return phoneUtil.isValidNumber(numberProto);
//        }catch (NumberParseException e){
//            System.err.println("isPhoneNumberValid NumberParseException was thrown: " + e.toString());
//        }
//        return false;
//    }

    /**
     *  隱藏 輸入字元
     *
     * @param view
     */
    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService( Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
