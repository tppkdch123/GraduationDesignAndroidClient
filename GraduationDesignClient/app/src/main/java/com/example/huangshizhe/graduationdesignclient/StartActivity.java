package com.example.huangshizhe.graduationdesignclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.huangshizhe.graduationdesignclient.enums.RequestEnum;
import com.example.huangshizhe.graduationdesignclient.utils.CommenUtil;
import com.example.huangshizhe.graduationdesignclient.utils.HttpConnectionUtil;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.HashMap;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String result = HttpConnectionUtil.getJsonResult(RequestEnum.AUTO_LOGIN, new HashMap<>());
            if (result != null) {
                try {
                    JsonNode jsonNode = CommenUtil.toJsonNode(result);
                    Integer status = jsonNode.get("status").asInt();
                    if (status == 0) {
                        Intent intent = new Intent(StartActivity.this, IndexActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }).start();
    }
}
