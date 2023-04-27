package com.example.demoapplication.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClickTestViewGroup extends ViewGroup {
    public ClickTestViewGroup(Context context) {
        this(context,null);
    }

    public ClickTestViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ClickTestViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        int measuredWidth = 0; //测量的宽
        int measuredHeight = 0;//测量的高
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize  = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int lineTop = getPaddingTop(); // 子view开始绘制的上基准线
        int lineLeft = getPaddingLeft();//子view 开始绘制的
        int maxLineWidth = widthSize-getPaddingRight(); //父元素总的宽度
        MarginLayoutParams marginLayoutParams;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            marginLayoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth()+marginLayoutParams.leftMargin + marginLayoutParams.rightMargin +child.getPaddingLeft()+child.getPaddingRight();
            int childHeight = child.getMeasuredHeight()+marginLayoutParams.topMargin +marginLayoutParams.bottomMargin+child.getPaddingTop()+child.getPaddingBottom();


            if (lineLeft+childWidth >maxLineWidth){
                lineTop+=childHeight;
                lineLeft = getPaddingLeft();
            }

            child.setTag(new ChildViewData(lineTop,lineLeft+childWidth,lineTop+childHeight,lineLeft));
            lineLeft  =  lineLeft+childWidth;
        }

//        Map<String, Integer> compute = compute(widthSize-getPaddingLeft()-getPaddingRight());

        measuredWidth = widthSize;
        measuredHeight = heightSize;
//        //EXACTLY模式：对应于给定大小或者match_parent情况
//        if (widthMode == MeasureSpec.EXACTLY) {
//            measuredWidth = widthSize;
//            //AT_MOS模式：对应wrap-content（需要手动计算大小，否则相当于match_parent）
//        } else if (widthMode == MeasureSpec.AT_MOST) {
//            measuredWidth = compute.get("allChildWidth");
//        }
//
//        if (heightMode == MeasureSpec.EXACTLY) {
//            measuredHeight = heightSize;
//        } else if (heightMode == MeasureSpec.AT_MOST) {
//            measuredHeight = compute.get("allChildHeight");
//        }
        setMeasuredDimension(measuredWidth,measuredHeight);
    }



    /**
     * 测量过程
     * @param flowWidth 该view的宽度
     * @return  返回子元素总所占宽度和高度（用于计算Flowlayout的AT_MOST模式设置宽高）
     */
    private Map<String, Integer> compute(int flowWidth) {
        //是否是单行
        boolean aRow = true;
        MarginLayoutParams marginParams;//子元素margin
        int rowsWidth = getPaddingLeft();//当前行已占宽度(注意需要加上paddingLeft)
        int columnHeight =getPaddingTop();//当前行顶部已占高度(注意需要加上paddingTop)
        int rowsMaxHeight = 0;//当前行所有子元素的最大高度（用于换行累加高度）

        for (int i = 0; i <  getChildCount(); i++) {

            View child = getChildAt(i);
            //获取元素测量宽度和高度
            int measuredWidth = child.getMeasuredWidth();
            int measuredHeight = child.getMeasuredHeight();
            Log.d("compute", "compute: "+measuredWidth+';'+measuredHeight);
            //获取元素的margin
            marginParams = (MarginLayoutParams) child.getLayoutParams();
            //子元素所占宽度 = MarginLeft+ child.getMeasuredWidth+MarginRight  注意此时不能child.getWidth,因为界面没有绘制完成，此时wdith为0
            int childWidth = marginParams.leftMargin + marginParams.rightMargin + measuredWidth;
            int childHeight = marginParams.topMargin + marginParams.bottomMargin + measuredHeight;
            //判断是否换行： 该行已占大小+该元素大小>父容器宽度  则换行

            rowsMaxHeight = Math.max(rowsMaxHeight, childHeight);
            //换行
            if (rowsWidth + childWidth > flowWidth) {
                //重置行宽度
                rowsWidth = getPaddingLeft()+getPaddingRight();
                //累加上该行子元素最大高度
                columnHeight += rowsMaxHeight;
                //重置该行最大高度
                rowsMaxHeight = childHeight;
                aRow = false;
            }
            //累加上该行子元素宽度
            rowsWidth += childWidth;
            //判断时占的宽段时加上margin计算，设置顶点位置时不包括margin位置，不然margin会不起作用，这是给View设置tag,在onlayout给子元素设置位置再遍历取出
            child.setTag(new Rect(rowsWidth - childWidth + marginParams.leftMargin, columnHeight + marginParams.topMargin, rowsWidth - marginParams.rightMargin, columnHeight + childHeight - marginParams.bottomMargin));
        }

        //返回子元素总所占宽度和高度（用于计算Flowlayout的AT_MOST模式设置宽高）
        Map<String, Integer> flowMap = new HashMap<>();
        //单行
        if (aRow) {
            flowMap.put("allChildWidth", rowsWidth);
        } else {
            //多行
            flowMap.put("allChildWidth", flowWidth);
        }
        //FlowLayout测量高度 = 当前行顶部已占高度 +当前行内子元素最大高度+FlowLayout的PaddingBottom
        flowMap.put("allChildHeight", columnHeight+rowsMaxHeight+getPaddingBottom());
        return  flowMap;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
//            Rect rect = (Rect) getChildAt(i).getTag();
//            child.layout(rect.left,rect.top,rect.right,rect.bottom);
            ChildViewData rect = (ChildViewData) getChildAt(i).getTag();
            child.layout(rect.getLeft(),rect.getTop(),rect.getRight(),rect.getBottom());
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("**GROUPVIEW**", "onTouchEvent: ");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("**GROUPVIEW**", "onInterceptTouchEvent: ");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("**GROUPVIEW**", "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }

    public void addTags(List<Data> list) {
        //往容器内添加TextView数据
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 5, 10, 5);
        removeAllViews();

        for (int i = 0; i < list.size(); i++) {
            Data data = list.get(i);
            TextView tv = new TextView(getContext());
            tv.setPadding(28, 10, 28, 10);
            tv.setText(data.getLabel());
            
            tv.setMaxEms(10);
            tv.setSingleLine();

            tv.setLayoutParams(layoutParams);
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), data.getValue(), Toast.LENGTH_SHORT).show();
                }
            });
            addView(tv, layoutParams);
        }
    }

    public void appendTags(List<String> list) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 5, 10, 5);
        for (int i = 0; i < list.size(); i++) {
            TextView tv = new TextView(getContext());
            tv.setPadding(28, 10, 28, 10);
            tv.setText(list.get(i));
            tv.setMaxEms(10);
            tv.setSingleLine();

            tv.setLayoutParams(layoutParams);
            addView(tv, layoutParams);
        }
    }
}
