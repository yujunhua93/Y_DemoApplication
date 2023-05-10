package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.demoapplication.FLowLayout.view.FlowLayout;
import com.example.demoapplication.FLowLayout.model.Data;
import com.example.demoapplication.FLowLayout.adapter.TagAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FlowLayout flowLayout;
    private List<Data> list=new ArrayList<>();
    TagAdapter mTagAdapter = new TagAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flowLayout = findViewById(R.id.ClickTestViewGroup);

        list.add(new Data("android","1"));
        list.add(new Data("Java","2"));
        list.add(new Data("IOS","3"));
        list.add(new Data("python","4"));

        list.add(new Data("android","1"));
        list.add(new Data("Java","2"));
        list.add(new Data("IOS","3"));
        list.add(new Data("python","4"));

        list.add(new Data("android","1"));
        list.add(new Data("Java","2"));
        list.add(new Data("IOS","3"));
        list.add(new Data("python","4"));

        list.add(new Data("android","1"));
        list.add(new Data("Java","2"));
        list.add(new Data("IOS","3"));
        list.add(new Data("python","4"));

        mTagAdapter.setData(list);
        flowLayout.setAdapter(mTagAdapter);
        mTagAdapter.notifyDataSetChange();

        flowLayout.setOnTagclickListener(new FlowLayout.OnTagclickListener() {
            @Override
            public void onTagClick(Data data) {


                Toast.makeText(MainActivity.this, data.getValue(), Toast.LENGTH_SHORT).show();
            }
        });

        flowLayout.setOnTagLongClickListener(new FlowLayout.OnTagLongClickListener() {
            @Override
            public void OnTagLongCLick(Data data) {
                Toast.makeText(MainActivity.this, data.getValue(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("**ACTIVITY**", "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }
}