package com.shiran.bmoblogindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String APP_ID = "539c660aac47ac691098966c6f4bae86"; //把你在Bmob官网获取的APPID放到这里
    private EditText mEtUserName = null;
    private EditText mEtPassWord = null;
    private Button mBtnGoLogin = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this,APP_ID); //初始化BmobSDK
        mEtUserName = (EditText) findViewById(R.id.et_user_name);
        mEtPassWord = (EditText) findViewById(R.id.et_user_pwd);
        mBtnGoLogin = (Button) findViewById(R.id.btn_go_login);
        mBtnGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void btnShow(View v) {
        final String username = mEtUserName.getText().toString();
        final String password = mEtPassWord.getText().toString();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(MainActivity.this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mEtUserName.length() < 4) {
            Toast.makeText(this, "用户名不能低于4位", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mEtPassWord.length() < 6) {
            Toast.makeText(this, "密码不能低于6位", Toast.LENGTH_SHORT).show();
            return;
        }

        /**
         * Bmob注册
         */
        final BmobUser user = new BmobUser();
        user.setUsername(username);
        user.setPassword(password);
        user.signUp(MainActivity.this, new SaveListener() { //回调2个方法，成功，失败
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "注册成功，去登录", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onSuccess: "+ "注册成功");
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(MainActivity.this, "注册失败，请检查用户名是否存在及网络问题", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: "+s.toString());

            }
        });
    }
}
