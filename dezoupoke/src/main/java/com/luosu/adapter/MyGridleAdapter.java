package com.luosu.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.luosu.dezoupoke.R;

import java.io.LineNumberInputStream;
import java.util.ArrayList;

/**
 * Created by User on 2017/2/28.
 */

public class MyGridleAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Integer> list;



    public MyGridleAdapter(Context context, ArrayList<Integer> list) {

        this.context = context;
        this.list = list;

    }


    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View contenteView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (null == contenteView) {
            contenteView = (View) View.inflate(context, R.layout.gride_item, null);
            holder = new ViewHolder();
            contenteView.setTag(holder);

        } else {
            holder=(ViewHolder)contenteView.getTag();
        }
        holder.img=(ImageView)contenteView.findViewById(R.id.img);
        holder.img.setImageDrawable(context.getResources().getDrawable(list.get(i)));
        return contenteView;
    }

    class ViewHolder {
        ImageView img;
    }
}
