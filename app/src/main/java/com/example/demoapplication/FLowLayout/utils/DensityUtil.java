package com.example.demoapplication.FLowLayout.utils;

import android.content.Context;

/**
 * @ClassName DensityUtil
 * @Author name
 * @Date 2023/5/10
 * @Description
 */
public  class DensityUtil {

    /**
     * dp转换成px
     */
    public static int dp2px(Context context, float dpValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }

    /**
     * px转换成dp
     */
    public static int px2dp(Context context,float pxValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);
    }
    /**
     * sp转换成px
     */
    public static int sp2px(Context context,float spValue){
        float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue*fontScale+0.5f);
    }
    /**
     * px转换成sp
     */
    public static int px2sp(Context context,float pxValue){
        float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue/fontScale+0.5f);
    }
}
