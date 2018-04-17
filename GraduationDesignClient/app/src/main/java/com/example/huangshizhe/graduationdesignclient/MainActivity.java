package com.example.huangshizhe.graduationdesignclient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huangshizhe.graduationdesignclient.utils.HttpConnectionUtil;
import com.example.huangshizhe.graduationdesignclient.utils.MyApplication;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int a=R.id.button;
        button=(Button)findViewById(R.id.button);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.text);
        editText = (EditText) findViewById(R.id.editText);
        button.setOnClickListener(listener -> {
            new sendTest().execute();
            Toast.makeText(MyApplication.getContext(), editText.getText(), Toast.LENGTH_LONG).show();
        });
    }
    class sendTest extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return HttpConnectionUtil.getJsonResult(editText.getText().toString(),new HashMap<>());
        }

        @Override
        protected void onPostExecute(String result){
            textView.setText(result);
        }
    }
}
