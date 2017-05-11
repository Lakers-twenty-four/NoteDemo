package com.example.administrator.notedemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/5/5 0005.
 */

public class MyOpenHelper extends SQLiteOpenHelper{
    public static final String TABLE_NAME="notes";
    public static final String CONTENT="content";
    public static final String ID="_id";
    public static final String TIME="time";
    private Cursor cursor;
    /*
        name:数据库的名字
        factory 目的创建cursor对象
        version 数据库的版本  从1开始
     */
    public MyOpenHelper(Context context) //父类没有无参的构造方法
    {
        super(context,"Note.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //id一般以下划线开头  _id  exec执行
        db.execSQL("CREATE TABLE "+TABLE_NAME
                +"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                CONTENT+" TEXT NOT NULL, "
                +TIME+" TEXT NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    
}
