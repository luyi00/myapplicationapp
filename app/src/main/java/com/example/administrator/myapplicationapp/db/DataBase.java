package com.example.administrator.myapplicationapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {
    private Context mContext;
    public static final String CREATE_USER="create table user(" +
            "user_phone text primary key,"
            +"user_password text,"
            +"head_image text)";
    public static final String CREATE_MESSAGE="create table message(" +
            "id integer primary key autoincrement,"
            +"user_phone text,"
            +"time text,"
            +"content text,"
            +"label text,"
            +"image_path text,"
            +"local text)";

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version){
        super(context,name,cursorFactory,version);
        mContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
    }
}