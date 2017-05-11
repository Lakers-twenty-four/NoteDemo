package com.example.administrator.notedemo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by Administrator on 2017/5/5 0005.
 */

public class AddActivity extends AppCompatActivity{
    private EditText c_eText;
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase mWriteDb;
    private Toolbar mtoobar;
    private ActionBar mActionBar;
    private TextView accomplish_tv;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initToolbar();
        initDB();
        initView();
        initMethed();
    }

    private void initMethed() {
        accomplish();

    }

    private void initToolbar() {
        mtoobar= (Toolbar) findViewById(R.id.addactivity_toobar);
        mtoobar.setTitle("备忘录");
        setSupportActionBar(mtoobar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.drawable.backup_menu);
    }

    private void initDB() {
        myOpenHelper = new MyOpenHelper(this);
        mWriteDb = myOpenHelper.getWritableDatabase();//获取数据库对象
    }

    private void initView() {
        c_eText= (EditText) findViewById(R.id.c_eText);
        c_eText.setFocusable(true);
        accomplish_tv= (TextView) findViewById(R.id.accomplish_tv);

    }



    private void accomplish() {
        accomplish_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTextToDB();
                finish();
            }
        });
    }


    public void addTextToDB(){
        ContentValues cv=new ContentValues();
        cv.put(MyOpenHelper.CONTENT,c_eText.getText().toString());
        cv.put(MyOpenHelper.TIME,getTime());
        mWriteDb.insert(MyOpenHelper.TABLE_NAME,null,cv);
    }
    public String getTime(){
        /*SimpleDateFormat mFormat=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date=new Date();
        String curTime=mFormat.format(date);*/
        Time t=new Time();
        t.setToNow();
        int year = t.year;
        int month = t.month;
        int monthDay = t.monthDay;
        int hour = t.hour;
        int minute = t.minute;
        int second = t.second;

        StringBuilder sb = new StringBuilder();
        sb.append(year+"年"+month+"月"+monthDay+"日"+"\b"+hour+":"+minute+":"+second);
        String curTime= sb.toString();
        return curTime;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
        }
        return true;
    }
}
