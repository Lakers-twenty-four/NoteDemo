package com.example.adminstrator.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.notedemo.MainActivity;
import com.example.administrator.notedemo.R;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class LoginActivity extends AppCompatActivity{
    private EditText et_username;
    private EditText et_code;
    private TextView tv_sendCode;
    private String username;
    private String SmsContent="您的验证码是%smscode%，有效期为%ttl%分钟。您正在使用%appname%的验证码。";

   /* private int num=60;
    private Handler mHandler=new Handler();
    private Runnable mRunable=new Runnable() {
        @Override
        public void run() {
           if(num>0){
               num--;
               tv_sendCode.setText("倒计时"+num);
               mHandler.postDelayed(this,1000);
               tv_sendCode.setClickable(false);
           }else {
               num=60;
               tv_sendCode.setClickable(true);
           }
        }
    };*/
    private String code;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initOnClick();
    }



    private void initOnClick() {
        tv_sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* mHandler.post(mRunable);*/
                sendCode();
            }
        });
    }

    private void sendCode() {
        username = et_username.getText().toString();
        BmobSMS.requestSMSCode(username,SmsContent, new QueryListener<Integer>() {
            @Override
            public void done(Integer smsId,BmobException ex) {
                Log.i("smile",ex.toString());
                if(ex==null){//验证码发送成功
                    Toast.makeText(getApplicationContext(),"短信发送成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"服务器忙……",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        et_username= (EditText) findViewById(R.id.et_username);
        et_code= (EditText) findViewById(R.id.et_code);
        tv_sendCode= (TextView) findViewById(R.id.tv_sendCode);
        username = et_username.getText().toString();
    }

    public void login(View v){
        username = et_username.getText().toString();
        code = et_code.getText().toString();
        MyUser user=new MyUser();
        user.setMobilePhoneNumber(username);//设置手机号码（必填）
        BmobUser.loginByAccount("11位手机号码", "用户密码", new LogInListener<MyUser>() {

            @Override
            public void done(MyUser user, BmobException e) {
                if(user!=null){
                    Log.i("smile","用户登陆成功");
                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });



    }
    }

