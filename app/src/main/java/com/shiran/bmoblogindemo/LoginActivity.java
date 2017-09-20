package com.shiran.bmoblogindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity {
    private EditText mEtUserName = null;
    private EditText mEtPassWord = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEtUserName = (EditText) findViewById(R.id.et_login_name);
        mEtPassWord = (EditText) findViewById(R.id.et_login_pwd);

    }

    public void btnShowLogin(View v) {
        final String username = mEtUserName.getText().toString();
        final String password = mEtPassWord.getText().toString();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        final BmobUser user = new BmobUser();
        user.setUsername(username);
        user.setPassword(password);
        user.login(LoginActivity.this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, LoginSuccessActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(LoginActivity.this, "登陆失败，请检查用户名及密码", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
