package com.yiyiba.photo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yiyiba.photo.R;
import com.yiyiba.photo.bean.User;
import com.yiyiba.photo.common.BaseActivity;
import com.yiyiba.photo.utlis.ActivityCollector;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_login_username;
    private EditText et_login_password;
    private Button btn_login;
    private TextView tv_to_register;
    private ImageView iv_back;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        et_login_username = (EditText) findViewById(R.id.et_login_username);
        et_login_password = (EditText) findViewById(R.id.et_login_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_to_register = (TextView) findViewById(R.id.tv_to_register);

        btn_login.setOnClickListener(this);
        tv_to_register.setOnClickListener(this);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("登录");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                submit();
                break;
            case R.id.tv_to_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            default:
                break;
        }
    }

    private void submit() {
        // validate
        String username = et_login_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = et_login_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        //开始登录
        login(username, password);
    }

    /**
     * 账号密码登录
     */
    private void login(String username, String password) {
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User bmobUser, BmobException e) {
                if (e == null) {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                    ActivityCollector.removeAllActivity();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "账号或密码错误！", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
