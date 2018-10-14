package com.itheima.jpushtest;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Apple on 2016/10/7.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
