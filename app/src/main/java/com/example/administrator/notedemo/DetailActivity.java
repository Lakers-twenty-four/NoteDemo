package com.example.administrator.notedemo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/5/6 0006.
 */

public class DetailActivity extends AppCompatActivity{

    private int id;
    private String content;
    private String time;
    private EditText delatil_content;
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase mWriteDb;
    private Toolbar mToolbar;
    private ActionBar mActionBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initToolbar();
        initData();
        initView();

        delatil_content.setText(content);
    }

    private void initToolbar() {
        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        mToolbar.setTitle("备忘录");
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.drawable.backup_menu);

    }

    private void initView() {

        delatil_content= (EditText) findViewById(R.id.delatil_content);
        myOpenHelper = new MyOpenHelper(this);
        mWriteDb = myOpenHelper.getWritableDatabase();
    }

    private void initData() {
        id = getIntent().getIntExtra(MyOpenHelper.ID, 0);
        content = getIntent().getStringExtra(MyOpenHelper.CONTENT);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.delete_menu:
                deleteDataFromDB();
                finish();
                break;
            case R.id.update_menu:
                upDateDbDataFromDB();
                finish();
                break;
            default:
        }
        return true;
    }

    private void deleteDataFromDB() {
        mWriteDb.delete(MyOpenHelper.TABLE_NAME,"_id="+id,null);
    }

    private void upDateDbDataFromDB() {
        String newContent = delatil_content.getText().toString();
        mWriteDb.execSQL("update "+ MyOpenHelper.TABLE_NAME +" set "+ MyOpenHelper.CONTENT+"=? where "+ MyOpenHelper.ID+"=?",
                new Object[]{newContent,id}
                );
    }
    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }


}
