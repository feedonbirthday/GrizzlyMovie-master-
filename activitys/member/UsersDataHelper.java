package com.cafenoion.grizzlymovie.activitys.member;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class UsersDataHelper extends SQLiteOpenHelper {

    // 資料庫版本
    private static final int DATABASE_VERSION = 1;

    // 資料庫名稱
    private static final String DATABASE_NAME = "UserManager.db";

    // 資料表名稱
    private static final String TABLE_USER = "user";

    // 資料表欄位名稱
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_phone";
    private static final String COLUMN_USER_PHONE = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    // 創建使用者資料表 sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER +
            "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT,"
            + COLUMN_USER_PHONE + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT" + ")";

    // 刪除整個資料表 sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    //
    public UsersDataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //清除資料表 CREATE_USER_TABLE
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    //更新一筆資料
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // 如果資料存在 先清除
        db.execSQL(DROP_USER_TABLE);

        // 重新產生一筆資料
        onCreate(db);

    }

    /**
     * 新增一筆資料
     * @param user
     */
    public void addUser(UsersModel user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PHONE, user.getPhone());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // 插入一筆
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    //取得所有使用者
    public List<UsersModel> getAllUser() {
        // 產生資料欄位陣列
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_PHONE,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD
        };
        List<UsersModel> userList = new ArrayList<>();
        // 建立排序字串
        String sortOrder = COLUMN_USER_NAME + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();

        // 執行SQL
        Cursor cursor = db.query(TABLE_USER,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);


        // 將資料表全部資料傳出
        if (cursor.moveToFirst()) {
            do {
                UsersModel user = new UsersModel();
                user.setId( Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPhone(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PHONE)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // 回傳資料表
        return userList;
    }

    /**
     * 更新一筆會員資料
     *
     * @param user
     */
    public void updateUser(UsersModel user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PHONE, user.getPhone());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * 刪除一筆會員資料
     * @param user
     */
    public void deleteUser(UsersModel user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * 檢查某筆資料是否存在
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // 執行SQL
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                     //columns to return
                selection,                   //columns for the WHERE clause
                selectionArgs,               //The values for the WHERE clause
                null,                //group the rows
                null,                 //filter by row groups
                null);               //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * 檢查使用者輸入是否正確
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // 執行SQL
        Cursor cursor = db.query(TABLE_USER,
                columns,                     //columns to return
                selection,                   //columns for the WHERE clause
                selectionArgs,               //WHERE
                null,                //groupsby
                null,                 //filter groups row
                null);               //order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}
