package com.example.huangshizhe.graduationdesignclient.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by huangshizhe on 2018/3/23.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate(){
        super.onCreate();
        context=this;
    }

    public static Context getContext(){
        return context;
    }

}
