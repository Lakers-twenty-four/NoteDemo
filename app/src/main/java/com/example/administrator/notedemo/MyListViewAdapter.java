package com.example.administrator.notedemo;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/5/6 0006.
 */

//定义ListView的数据适配器
public class MyListViewAdapter extends BaseAdapter{
    private Cursor cursor;
    private Context context;
    private View view;
    private LayoutInflater layout;

    MyListViewAdapter(Context context, Cursor cursor){
        this.context=context;
        this.cursor=cursor;
    }
    //一共有多少条数据需要显示
    public int getCount() {
        return cursor.getCount();
    }

    //返回指定position位置对应的对象
    public Object getItem(int position) {
        return cursor.getPosition();
    }

    //返回指定position位置对应的Id
    public long getItemId(int position) {
        return position;
    }

    /*
    获取一个view，用来显示ListView的一个Item出现
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView cell_content;
        TextView cell_time;
        if(convertView==null){
            layout= LayoutInflater.from(context);
            view = layout.inflate(R.layout.activity_cell, parent, false);
            cell_content= (TextView) this.view.findViewById(R.id.cell_content);
            cell_time= (TextView) this.view.findViewById(R.id.cell_time);

            cursor.moveToPosition(position);
            final String content = cursor.getString(cursor.getColumnIndex("content"));
            String time = cursor.getString(cursor.getColumnIndex("time"));


            cell_content.setText(content);
            cell_time.setText(time);
        }else{
            view=convertView;
        }
        return view;
    }
}
