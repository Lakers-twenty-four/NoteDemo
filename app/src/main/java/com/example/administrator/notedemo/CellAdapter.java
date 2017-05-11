package com.example.administrator.notedemo;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by Administrator on 2017/5/5 0005.
 */

//ViewHolder--是我们CellAdapter中定义的一个内部类
public class CellAdapter extends RecyclerView.Adapter<CellAdapter.ViewHolder>{
    private Cursor cursor;
    private Context context;
    CellAdapter(Context context,Cursor cursor){
        this.context=context;
        this.cursor=cursor;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView cell_content,cell_time;

        /*View就是RecyclerView子项的最外层布局，
        那么我们可以通过findViewById()方法来获取布局实例*/
        public ViewHolder(View itemView) {
            super(itemView);
            cell_content= (TextView) itemView.findViewById(R.id.cell_content);
            cell_time= (TextView) itemView.findViewById(R.id.cell_time);

        }
 }

    /*onCreateViewHolder()是用于创建ViewHolder实例的
    * 我们将activity_cell布局加载进来，然后创建ViewHolder实例
    *并将加载出来的布局传入到构造函数当中去
    * 最后将+ViewHolder的实例返回
    * */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context)
                .inflate(R.layout.activity_cell,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        final String content = cursor.getString(cursor.getColumnIndex("content"));
        String time = cursor.getString(cursor.getColumnIndex("time"));

        holder.cell_content.setText(content);
        holder.cell_time.setText(time);

    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }



}
