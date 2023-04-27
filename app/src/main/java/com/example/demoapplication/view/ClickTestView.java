package com.example.demoapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class ClickTestView extends View {
    public ClickTestView(Context context) {
        this(context,null);
    }

    public ClickTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ClickTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("**VIEW**", "onTouchEvent: ");
        return super.onTouchEvent(event);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {


        super.onDraw(canvas);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("**VIEW**", "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(event);
    }
}
