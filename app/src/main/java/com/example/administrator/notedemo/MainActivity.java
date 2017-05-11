package com.example.administrator.notedemo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {


    //private RecyclerView rv;
    private Intent intent;
    private MyOpenHelper mDb;
    private SQLiteDatabase mReadDb;
    private Cursor cursor;
    private CellAdapter cellAdapter;
    private Toolbar mtoobar;
    private FloatingActionButton fab;
    private ListView lv;
    private MyListViewAdapter myListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mDb = new MyOpenHelper(this);
        mReadDb = mDb.getReadableDatabase();

    }

    private void initView() {
        mtoobar = (Toolbar) findViewById(R.id.toobar);
        mtoobar.setTitle("");
        setSupportActionBar(mtoobar);
        final ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.drawable.backup_menu);
        /*rv = (RecyclerView) findViewById(R.id.rv);*/
        lv= (ListView) findViewById(R.id.lv);
        fab= (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getApplicationContext(),AddActivity.class);
                intent.putExtra("flag",1);
                startActivity(intent);
            }
        });
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

    public void getDb(){
        cursor = mReadDb.query(MyOpenHelper.TABLE_NAME, null, null, null, null, null, null,null);
        /*cellAdapter = new CellAdapter(getApplicationContext(),cursor);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(manager);
        rv.setAdapter(cellAdapter);*/
        myListViewAdapter = new MyListViewAdapter(getApplicationContext(), cursor);
        lv.setAdapter(myListViewAdapter);

        //设置ListView的点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);

                Intent intent=new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra(MyOpenHelper.ID,cursor.getInt(cursor.getColumnIndex(MyOpenHelper.ID)));
                intent.putExtra(MyOpenHelper.CONTENT,cursor.getString(cursor.getColumnIndex(MyOpenHelper.CONTENT)));
                intent.putExtra(MyOpenHelper.TIME,cursor.getString(cursor.getColumnIndex(MyOpenHelper.TIME)));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDb();
    }

}
