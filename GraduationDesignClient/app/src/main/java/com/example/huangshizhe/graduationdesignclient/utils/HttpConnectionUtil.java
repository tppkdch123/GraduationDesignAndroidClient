package com.example.huangshizhe.graduationdesignclient.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by huangshizhe on 2018/3/23.
 */

public class HttpConnectionUtil {

    private static final String prefix="http://127.0.0.1:8080/";

    public static boolean isOnline() {
        ConnectivityManager cManager = (ConnectivityManager) MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获取当前活动的默认网络信息
        NetworkInfo networkInfo = cManager.getActiveNetworkInfo();
        // 判断是否存在默认网络，及默认网络是否开启 && 判断连接是否存在，是否可能建立连接进行数据传输
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        Toast.makeText(MyApplication.getContext(), "请打开网络", Toast.LENGTH_LONG).show();
        return false;
    }

    public static String getJsonResult(String url){
        HttpURLConnection conn=null;
        try {
            URL myurl=new URL(prefix+url);
            conn=(HttpURLConnection)myurl.openConnection();
            conn.setConnectTimeout(1000);
            conn.setReadTimeout(1000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
                return getJsonFromConnection(conn);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getJsonFromConnection(HttpURLConnection conn){
        StringBuffer result=new StringBuffer();
        try {
            InputStream inputStream=conn.getInputStream();
            Reader reader=new InputStreamReader(inputStream, Charset.defaultCharset());
            BufferedReader bufferedReader=new BufferedReader(reader);
            String str=bufferedReader.readLine();
            result.append(str);
            while((str = bufferedReader.readLine())!=null){
                result.append("\r\n");
                result.append(str);
            }
            bufferedReader.close();
            inputStream.close();
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}