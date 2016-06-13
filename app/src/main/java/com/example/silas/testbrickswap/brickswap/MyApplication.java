package com.example.silas.testbrickswap.brickswap;

import android.app.Application;
import android.content.Context;

/**
 * Created by Silas on 05-06-2016.
 */
public class MyApplication extends Application{
    private static MyApplication sInstance;
    public static String url;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MyApplication getInstance(){
        return sInstance;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }
}
