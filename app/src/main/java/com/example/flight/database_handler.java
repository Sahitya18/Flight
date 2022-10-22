package com.example.flight;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class database_handler extends SQLiteOpenHelper {

    private final static String signup_name="signup_db";

    public database_handler(@Nullable Context context) {
        super(context, signup_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sql) {
        String query= "create table users(user_id integer primary key autoincrement, user_name text, user_email text, user_mobileNumber number, user_password text, user_gender text)";
        sql.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void input(String username,String userEmail,Long userMobileNo,String userPassword,String userGender)
    {
        SQLiteDatabase sql_obj=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("user_name",username);
        c.put("user_email",userEmail);
        c.put("user_mobileNumber",userMobileNo);
        c.put("user_password",userPassword);
        c.put("user_gender",userGender);

    }
}
