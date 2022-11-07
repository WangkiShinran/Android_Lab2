package com.example.myapplication;

import static android.app.Activity.RESULT_OK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoyanzhuo
 * @create 2022/8/29
 * @Describe
 */
public class ThirdAdapter extends RecyclerView.Adapter<ThirdAdapter.TextViewHolder> {
    private ThirdActivity thirdActivity;
    private List<String> mItems = new ArrayList<>();
    private TextViewHolder t;

    public ThirdAdapter(@NonNull ThirdActivity a) {
        thirdActivity = a;
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        t = new TextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false));
        return t;
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void notifyItems(List<String> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public class TextViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text);
            itemView.setOnClickListener(this);
        }

        public void bind(String text) {
            mTextView.setText(text);
        }

        @Override
        public void onClick(View view) {
            //deal with click event
            Intent intent = new Intent(view.getContext(), MyActivity.class);
            String str = mTextView.getText().toString();
            int id = mItems.indexOf(str);
            intent.putExtra("extra", id); //取标号
            //view.getContext().startActivity(intent); //不回传数据的用法
            thirdActivity.startActivityForResult(intent, 1);
        }
    }

    public void Refresh(int pos)
    {
        mItems.set(pos, thirdActivity.returnData);
        notifyItemChanged(pos);
    }
}
