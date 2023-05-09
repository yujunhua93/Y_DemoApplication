package com.example.demoapplication.FLowLayout.adapter;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public  abstract  class BaseAdapter {
    private final DataSetObservable mDataSetObservable = new DataSetObservable();

    private final ArrayList<View> views = new ArrayList<View>();
    public void registerDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.registerObserver(observer);
    }

    public void unRegisterDataSetObserver(DataSetObserver observer){
        mDataSetObservable.unregisterObserver(observer);
    }


    public void notifyDataSetChange(){
        mDataSetObservable.notifyChanged();
    }

    public abstract int getCounts();

    abstract View getView(int position, ViewGroup parent);


    public void addViewToList(ViewGroup parent){
        views.clear();
        int counts = getCounts();

        if(counts == 0){
            return;
        }

        for (int i = 0; i < counts; i++) {
            views.add(getView(i,parent));
        }
    }


    public ArrayList<View> getViewsList(){
        return  views;
    }



}
