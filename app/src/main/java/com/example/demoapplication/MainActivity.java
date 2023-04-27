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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ClickTestViewGroup clickTestViewGroup;
    private List<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clickTestViewGroup = findViewById(R.id.ClickTestViewGroup);

        for (int i = 0; i <10; i++) {
            list.add("Android");
            list.add("Java");
            list.add("IOS");
            list.add("python");
        }
        clickTestViewGroup.addTags(list);

        clickTestViewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickTestViewGroup.appendTags(list);
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("**ACTIVITY**", "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }
}