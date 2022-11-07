package com.example.myapplication;

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
        holder.bind(mItems.get(position), position);
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
        public int position = 0;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text);
            itemView.setOnClickListener(this);
        }

        public void bind(String text, int pos) {
            mTextView.setText(text);
            position = pos;
        }

        @Override
        public void onClick(View view) {
            //deal with click event
            Intent intent = new Intent(view.getContext(), MyActivity.class);
//            String str = mTextView.getText().toString();
//            intent.putExtra("extra", str.substring(7)); //取最后的标号
            Log.e("wdw", "pos = " + position);
            intent.putExtra("extra", position);
            //view.getContext().startActivity(intent); //不回传数据的用法
            thirdActivity.startActivityForResult(intent, 1);
        }

        public void refresh(int pos) {
            mItems.remove(pos);
            Log.e("wdw", "refresh pos = " + position);
            mItems.add(pos, thirdActivity.returnData);
            notifyDataSetChanged();
        }

    }

    public void Refresh(int pos)
    {
        t.refresh(pos);
    }
}
