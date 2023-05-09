package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoapplication.view.ClickTestViewGroup;
import com.example.demoapplication.view.Data;
import com.example.demoapplication.view.TagAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ClickTestViewGroup clickTestViewGroup;
    private List<Data> list=new ArrayList<>();
    TagAdapter mTagAdapter = new TagAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clickTestViewGroup = findViewById(R.id.ClickTestViewGroup);

        list.add(new Data("android","1"));
        list.add(new Data("Java","2"));
        list.add(new Data("IOS","3"));
        list.add(new Data("python","4"));


        mTagAdapter.setData(list);
        clickTestViewGroup.setAdapter(mTagAdapter);
        mTagAdapter.notifyDataSetChange();

        clickTestViewGroup.setOnTagclickListener(new ClickTestViewGroup.OnTagclickListener() {
            @Override
            public void onTagClick() {
                Toast.makeText(MainActivity.this, "onTagClick", Toast.LENGTH_SHORT).show();
            }
        });

        clickTestViewGroup.setOnTagLongClickListener(new ClickTestViewGroup.OnTagLongClickListener() {
            @Override
            public void OnTagLongCLick() {
                Toast.makeText(MainActivity.this, "OnTagLongCLick", Toast.LENGTH_SHORT).show();
            }
        });




//

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("**ACTIVITY**", "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }
}