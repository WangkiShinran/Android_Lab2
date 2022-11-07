package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class lab4 extends AppCompatActivity {
    private Button btn1, btn2, btn3, btn4;
    private TextView tv;
    private boolean first = true;
    private int leftTime = 10;

    Handler handler = new Handler();
    Runnable update_thread = new Runnable() {
        @Override
        public void run() {
            leftTime--;
            if (leftTime >= 0) {
                //倒计时效果展示
                String s = ""+leftTime;
                tv.setText(s);
                //每一秒执行一次
                handler.postDelayed(this, 1000);
            } else {//倒计时结束
                handler.removeCallbacks(this);
                Toast.makeText(lab4.this, "时间到！", Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab4);

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        tv=findViewById(R.id.timer);

        btn1.setOnClickListener(new View.OnClickListener() {  //开始键
            @Override
            public void onClick(View view) {
                leftTime=10;
                handler.postDelayed(update_thread, 1000);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {  //停止键
            @Override
            public void onClick(View view) {
                leftTime=10;
                String s=""+leftTime;
                tv.setText(s);  //复位
                handler.removeCallbacks(update_thread);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {  //暂停键
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(update_thread);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {  //继续键
            @Override
            public void onClick(View view) {
                handler.postDelayed(update_thread, 1000);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        leftTime = 0;
        handler.removeCallbacks(update_thread);
    }
}