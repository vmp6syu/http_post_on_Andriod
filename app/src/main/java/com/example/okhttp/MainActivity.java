package com.example.okhttp;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.logging.Handler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private Button btnCode;
    private  TextView textView;
    private EditText ed1;
    private EditText ed2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }
    private void initView() {
        btnCode = (Button) findViewById(R.id.btn_regester);
        textView = (TextView) findViewById(R.id.text);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);
        btnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDataParame();
            }
        });
    }



    private void postDataParame(){
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add("username",ed1.getText().toString());
        formBody.add("password",ed2.getText().toString());
        Request request = new Request.Builder()
                .url("http://140.125.45.146:8000/login/")
                .post(formBody.build())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                if(!response.isSuccessful()){
//                    throw new IOException("Unexpected code"+response);
//                }else{
//                    final  String resStr = response.body().string();
//                    textView.setText(resStr);
//                }
                final  String resStr = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(resStr);
                    }
                });

            }
        });


    }
//    private void sendByOKHttp(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                OkHttpClient client = new OkHttpClient();
//                Request request = new Request.Builder().url("http://140.125.45.146:8000/index_min/").build();
//                try {
//                    Response response = client.newCall(request).execute();
//                    String result=response.body().string();
//                    Log.d("test","result"+result);
//                   // show(result);
//                }catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();
//
//    }

}
