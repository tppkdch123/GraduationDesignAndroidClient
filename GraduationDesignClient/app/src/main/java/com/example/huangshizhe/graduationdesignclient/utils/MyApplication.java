package com.example.huangshizhe.graduationdesignclient.utils;

import android.app.Application;
import android.content.Context;

import com.example.huangshizhe.graduationdesignclient.R;

import java.io.IOException;
import java.util.Properties;

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
    private static Properties properties;

    static {
        try {
          properties.load(MyApplication.getContext().getResources().openRawResource(R.raw.cookie));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Context getContext(){
        return context;
    }

    public static String getSSOToken(){
      return properties.getProperty("huangshizhetianxiadiyi","");
    }
}
