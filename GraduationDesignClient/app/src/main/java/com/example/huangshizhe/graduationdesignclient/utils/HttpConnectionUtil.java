package com.example.huangshizhe.graduationdesignclient.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.huangshizhe.graduationdesignclient.enums.RequestEnum;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by huangshizhe on 2018/3/23.
 */

public class HttpConnectionUtil {

    private static final String prefix = "http://192.168.1.7:8080";

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

    public static String getJsonResult(RequestEnum requestEnum, Map<String, String> params) {
        if (!isOnline()) {
            Toast.makeText(MyApplication.getContext(), "无法连接服务器，请检查网络", Toast.LENGTH_LONG).show();
        }
        if (requestEnum.getType().equals("GET")) {
            return getJsonResult(requestEnum.getUrl(), params);
        }
        return null;
    }

    public static String getJsonResult(String url, Map<String, String> params) {
        HttpURLConnection conn = null;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("?");
            for (Map.Entry entry : params.entrySet()) {
                stringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            URL myurl = new URL(prefix + url + stringBuilder.toString());
            conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(2000);
            conn.setReadTimeout(3000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Cookie","huangshizhetianxiadiyi="+CommenUtil.getCookie());
            conn.setDoInput(true);
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return getJsonFromConnection(conn);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getJsonFromConnection(HttpURLConnection conn) {
        StringBuffer result = new StringBuffer();
        try {
            String cookies = conn.getHeaderField("Set-Cookie");
            String token = StringUtils.substringBetween(cookies, "huangshizhetianxiadiyi=", ";");
            System.out.println("xxxxxx"+token);
            if (StringUtils.isNotBlank(token)) {
                CommenUtil.setCookie(token);
            }
            InputStream inputStream = conn.getInputStream();
            Reader reader = new InputStreamReader(inputStream, Charset.defaultCharset());
            BufferedReader bufferedReader = new BufferedReader(reader);
            String str = bufferedReader.readLine();
            result.append(str);
            while ((str = bufferedReader.readLine()) != null) {
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
