package com.example.huangshizhe.graduationdesignclient;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.huangshizhe.graduationdesignclient.enums.RequestEnum;
import com.example.huangshizhe.graduationdesignclient.utils.CommenUtil;
import com.example.huangshizhe.graduationdesignclient.utils.HttpConnectionUtil;
import com.example.huangshizhe.graduationdesignclient.utils.MyApplication;
import com.fasterxml.jackson.databind.JsonNode;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView email;

    private EditText password;

    private TextInputEditText verification;

    private TextInputLayout passwordLayout;

    private LinearLayout verLayout;

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);

        password = findViewById(R.id.password);

        verification = findViewById(R.id.verification);

        passwordLayout = findViewById(R.id.password_layout);

        verLayout = findViewById(R.id.ver_layout);

        findViewById(R.id.login).setOnClickListener((v) -> new LoginByPassword().execute(true));

        findViewById(R.id.button3).setOnClickListener((v) -> new LoginByPassword().execute(false));

        email.setText(CommenUtil.getEmail(this));

        password.setText(CommenUtil.getPassword(this));

        findViewById(R.id.register).setOnClickListener((v) -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });


    }

    class LoginByPassword extends AsyncTask<Boolean, Void, String> {

        @Override
        protected String doInBackground(Boolean... bs) {
            CommenUtil.saveEmail(context, email.getText().toString());
            if (bs[0]) {
                String verificationText = verification.getText().toString();
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                if (StringUtils.isEmpty(verificationText)) {
                    Map<String, String> params = new HashMap<>();
                    params.put("email", emailText);
                    params.put("password", passwordText);
                    CommenUtil.savePassword(context, passwordText);
                    return HttpConnectionUtil.getJsonResult(RequestEnum.LOGIN_PASSWORD, params);
                } else {
                    Map<String, String> params = new HashMap<>();
                    params.put("verificationCode", verificationText);
                    params.put("email", emailText);
                    return HttpConnectionUtil.getJsonResult(RequestEnum.LOGIN_VER, params);
                }
            } else {
                String emailText = email.getText().toString();
                Map<String, String> params = new HashMap<>();
                params.put("email", emailText);
                return HttpConnectionUtil.getJsonResult(RequestEnum.SEND_EMAIL, params);
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                if (StringUtils.isEmpty(result)) {
                    Toast.makeText(MyApplication.getContext(), "返回结果为空", Toast.LENGTH_LONG).show();
                    return;
                }
                JsonNode jsonNode = CommenUtil.toJsonNode(result);
                Integer integer = jsonNode.get("status").asInt();
                if (integer == 0) {
                    Intent intent = new Intent(LoginActivity.this, IndexActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                } else {
                    Toast.makeText(MyApplication.getContext(), jsonNode.get("data").asText(), Toast.LENGTH_LONG).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
