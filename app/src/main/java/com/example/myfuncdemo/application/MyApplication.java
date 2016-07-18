package com.example.myfuncdemo.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Administrator on 2016/7/12.
 */
public class MyApplication extends Application{

    private static MyApplication _instance;

    public static MyApplication getInstance(){
        if(_instance ==null){
            _instance = new MyApplication();
        }
        return _instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;
        Fresco.initialize(this);
    }
}
