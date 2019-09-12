package com.example.administrator.myapplicationapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {
    private Context mContext;
    private static final String CREATE_USER="create table user(" +
            "user_phone text primary key,"
            +"user_password text,"
            +"head_image text)";
    private static final String CREATE_MESSAGE="create table personalMessage(" +
            "user_phone text primary key,"
            +"head_image text,"
            +"user_name text,"
            +"user_sex text,"
            +"user_birthday text,"
            +"user_introduce text)";

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version){
        super(context,name,cursorFactory,version);
        mContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_MESSAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
    }
}