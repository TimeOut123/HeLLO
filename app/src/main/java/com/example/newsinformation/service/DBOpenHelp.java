package com.example.newsinformation.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelp extends SQLiteOpenHelper {
    final static String CREATE_TEBLE_SQL="create table users(uid integer primary key autoincrement, uname text, upassword text,usex integer,uphone text )";
    static String name = "newsinformation.db";
    static  int  version = 1;
    public DBOpenHelp(@Nullable Context context) {
        super(context, name, null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TEBLE_SQL);//创建数据库表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //更新数据库
    }
}
