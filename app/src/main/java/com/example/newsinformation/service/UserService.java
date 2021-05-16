package com.example.newsinformation.service;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.newsinformation.activity.login.LoginActivity;
import com.example.newsinformation.po.User;

public class UserService {
    private SQLiteDatabase db;
    private DBOpenHelp dbOpenHelp;
    private  User user;

    public UserService(Context context) {
        dbOpenHelp = new DBOpenHelp(context);
    }

    public boolean insert(User user){
        String sql = "insert into users (uname,upassword,usex,uphone) values(?,?,?,?)";
        db = dbOpenHelp.getWritableDatabase();

        try {
            db.execSQL(sql,new String[]{user.getUname(),user.getUpassword(), String.valueOf(user.getUsex()),user.getUphone()});
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            Log.d("insert","insert succeeded");
            db.close();
        }
        return  true;
    }
    public boolean update(Integer uid,User user){
        String sql = "update users set uname=?,upassword=?,usex=?,uphone=? where uid=?";
        db = dbOpenHelp.getWritableDatabase();
        try {
            db.execSQL(sql,new String[]{user.getUname(),user.getUpassword(), String.valueOf(user.getUsex()),user.getUphone(), String.valueOf(uid)});
            Log.v("update","update succeeded");
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        } finally {
            db.close();
        }
        return true;
    }
    public boolean delete(Integer uid){
        String sql = "delete * from users where uid ="+uid;
        db = dbOpenHelp.getWritableDatabase();
        try {
            db.execSQL(sql);
            Log.v("delete","delete succeeded");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
       return true;
    }
    public User findById(Integer uid){
        String sql = "select * from users where uid=?";
        db = dbOpenHelp.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,new String[]{String.valueOf(uid)});
        while (cursor.moveToNext()){
            user= new User();
            user.setUid(uid);
            user.setUname(cursor.getString(cursor.getColumnIndex("uname")));
            user.setUpassword(cursor.getString(cursor.getColumnIndex("upassword")));
            user.setUsex(cursor.getInt(cursor.getColumnIndex("usex")));
            user.setUphone(cursor.getString(cursor.getColumnIndex("uphone")));
            Log.v("user:","id"+user.getUid()+"uname"+user.getUname() + "upassword"
                    +user.getUpassword()+"usex"+user.getUsex()+"uphone"+user.getUphone());
        }
        db.close();
        return user;
    }
    public boolean findByUnameAndPassword(User user){
        String sql = "select * from users where uname=? and upassword=?";
        db = dbOpenHelp.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,new String[]{user.getUname(),user.getUpassword()});

        if(cursor==null||cursor.getCount()==0){
            return false;
        }
        db.close();
        return true;
    }
}
