package com.example.demoapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.demoapplication.R;

public class Tag extends LinearLayout {
    private Context context;
    private TextView tagTv;
    private ImageView tagCloseIv;
    public Tag(Context context) {
        this(context,null);
    }

    public Tag(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Tag(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        View view =  LayoutInflater.from(context).inflate(R.layout.tag_item_layout,null);
        tagTv = view.findViewById(R.id.tag_tv);
        tagCloseIv = view.findViewById(R.id.tag_close);

        tagTv.setPadding(28, 10, 28, 10);
        addView(view);
    }

    public void setTagText(String text){
        tagTv.setText(text);
        tagTv.setMaxEms(10);
        tagTv.setSingleLine();

    }

    public void setCloseShow(boolean show){
        tagCloseIv.setVisibility(show ? View.VISIBLE:View.GONE);

    }


}
