package com.example.huangshizhe.graduationdesignclient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.huangshizhe.graduationdesignclient.enums.RequestEnum;
import com.example.huangshizhe.graduationdesignclient.utils.HttpConnectionUtil;
import com.example.huangshizhe.graduationdesignclient.utils.MyApplication;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private AutoCompleteTextView email;

    private TextInputEditText verificationCode;

    private Button send;

    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_register);

        email=findViewById(R.id.email_register);

        verificationCode=findViewById(R.id.verification_register);

        send=findViewById(R.id.button4);

        register=findViewById(R.id.register2);

        send.setOnClickListener((v)->new sendRegisterEmail().execute(email.getText().toString()));

        register.setOnClickListener((v)->new register().execute(email.getText().toString(),verificationCode.getText().toString()));

    }

    class sendRegisterEmail extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String email=strings[0];
            Map<String,String> params=new HashMap<>();
            params.put("email",email);
            return HttpConnectionUtil.getJsonResult(RequestEnum.SEND_EMAIL_REGISTER,params);
        }

        @Override
        protected void onPostExecute(String result){
            Toast.makeText(MyApplication.getContext(), result, Toast.LENGTH_LONG).show();
        }
    }
    class register extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String email=strings[0];
            String code=strings[1];
            Map<String,String> params=new HashMap<>();
            params.put("email",email);
            params.put("code",code);
            return HttpConnectionUtil.getJsonResult(RequestEnum.REGISTER,params);
        }

        @Override
        protected void onPostExecute(String result){
            Toast.makeText(MyApplication.getContext(), result, Toast.LENGTH_LONG).show();
        }
    }
}
