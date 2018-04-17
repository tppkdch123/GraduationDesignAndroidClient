package com.example.huangshizhe.graduationdesignclient;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.huangshizhe.graduationdesignclient.enums.RequestEnum;
import com.example.huangshizhe.graduationdesignclient.utils.HttpConnectionUtil;
import com.example.huangshizhe.graduationdesignclient.utils.MyApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView email;

    private EditText password;

    private AutoCompleteTextView verification;

    private TextInputLayout passwordLayout;

    private LinearLayout verLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=(AutoCompleteTextView)findViewById(R.id.email);

        password=(EditText) findViewById(R.id.password);

        verification=(AutoCompleteTextView)findViewById(R.id.verification);

        passwordLayout=(TextInputLayout)findViewById(R.id.password_layout);

        verLayout=(LinearLayout) findViewById(R.id.ver_layout);

        ((Button)findViewById(R.id.login)).setOnClickListener((v)->loginBypassword());
    }

    private void loginBypassword(){
        String emailText=email.getText().toString();
        String passwordText=password.getText().toString();
        Map<String,String> params=new HashMap<>();
        params.put("email",emailText);
        params.put("password",passwordText);
        Toast.makeText(MyApplication.getContext(),HttpConnectionUtil.getJsonResult(RequestEnum.LOGIN,params),Toast.LENGTH_LONG).show();
    }
}
