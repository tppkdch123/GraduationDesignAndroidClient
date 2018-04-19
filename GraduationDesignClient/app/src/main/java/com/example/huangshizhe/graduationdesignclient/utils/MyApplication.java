package com.example.huangshizhe.graduationdesignclient.utils;

import android.app.Application;
import android.content.Context;

import com.example.huangshizhe.graduationdesignclient.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by huangshizhe on 2018/3/23.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }

    public synchronized static String getSSOToken() {
        Properties properties = new Properties();
        try {
            File file = new File(getContext().getFilesDir(), "cookie.properties");
            if (!file.exists()) {
                System.out.println(file.mkdir());
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("huangshizhetianxiadiyi", "kong");
    }

    public synchronized static void setSSOToken(String token) {
        Properties properties = new Properties();
        try {
            File file = new File(getContext().getFilesDir(), "cookie.properties");
            FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            properties.load(fileInputStream);
            properties.setProperty("huangshizhetianxiadiyi", token);
            properties.store(fileOutputStream, "cookie.properties");
            fileOutputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static String getEmail() {
        Properties properties = new Properties();
        try {
            File file = new File(getContext().getFilesDir(), "cookie.properties");
            if (!file.exists()) {
                System.out.println(file.mkdir());
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("email", "xx");
    }

    public synchronized static void setEmail(String email) {
        Properties properties = new Properties();
        try {
            File file = new File(getContext().getFilesDir(), "cookie.properties");
            FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            properties.load(fileInputStream);
            properties.setProperty("email", email);
            properties.store(fileOutputStream, "cookie.properties");
            fileOutputStream.close();
            fileInputStream.close();
            System.out.println(properties.getProperty("email"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
