package com.example.demoapplication.view;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demoapplication.R;

import java.util.List;

public class TagAdapter extends BaseAdapter {

    private Context context;
    List<Data> dataList;

    public TagAdapter(Context context) {
        this.context = context;
    }

    public TagAdapter(Context context, List<Data> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void setData(List data){
        this.dataList = data;
    }


    @Override
    int getCounts() {
      return  dataList.size();
    }

    @Override
    View getView(int position, ViewGroup viewGroup) {
        View view =  LayoutInflater.from(context).inflate(R.layout.tag_item_layout,null);
        TextView textView = view.findViewById(R.id.tag_tv);
        textView.setText(dataList.get(position).getLabel());
        return view;
    }
}
