package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author gaoyanzhuo
 * @create 2022/8/29
 * @Describe
 */
public class MyActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity_LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        // receive data from last activity, and set it to a textview
        int extra = getIntent().getIntExtra("extra", -1);
        String full_text = "这是从item" + extra + "进入的";
        TextView receiveContent = findViewById(R.id.receive_content);
        receiveContent.setText(full_text);

        EditText editText = findViewById(R.id.edit_text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i(TAG, "beforeTextChanged :" + charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i(TAG, "onTextChanged :" + charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.i(TAG, "afterTextChanged :" + editable);
            }
        });
        // return data, finish activity
        TextView finishBtn = findViewById(R.id.finish_activity_btn);
        finishBtn.setOnClickListener((View v) -> {
            Intent intent = new Intent();
            intent.putExtra("pos_return", getIntent().getIntExtra("extra", -1));
            intent.putExtra("data_return", editText.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
