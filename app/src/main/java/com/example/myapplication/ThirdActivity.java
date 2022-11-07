package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoyanzhuo
 * @create 2022/8/29
 * @Describe
 */
public class ThirdActivity extends AppCompatActivity {
    protected RecyclerView mRecyclerView;
    private ThirdAdapter searchAdapter = new ThirdAdapter(this);
    private static final String TAG = "ThirdActivity_LOG";
    protected String returnData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(searchAdapter);

        List<String> items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            items.add("这是item " + String.valueOf(i));
        }
        searchAdapter.notifyItems(items);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));//添加下划线
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    returnData = data.getStringExtra("data_return");
                    int position = data.getIntExtra("position", 0);
                    Log.i(TAG, returnData);
                    searchAdapter.Refresh(position);
                }
                break;
            default:
        }
    }
}
