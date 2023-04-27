package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demoapplication.view.ClickTestViewGroup;
import com.example.demoapplication.view.Data;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ClickTestViewGroup clickTestViewGroup;
    private List<Data> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clickTestViewGroup = findViewById(R.id.ClickTestViewGroup);

        list.add(new Data("android","1"));
        list.add(new Data("Java","2"));
        list.add(new Data("IOS","3"));
        list.add(new Data("python","4"));


        clickTestViewGroup.addTags(list);

//        clickTestViewGroup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                clickTestViewGroup.appendTags(list);
//            }
//        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("**ACTIVITY**", "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }
}