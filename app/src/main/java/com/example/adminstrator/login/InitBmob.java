package com.example.adminstrator.login;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class InitBmob extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //第一：默认初始化
        Bmob.initialize(this,"c2cf3280eabb60e2866b348dbaddff5e");
    }
}
