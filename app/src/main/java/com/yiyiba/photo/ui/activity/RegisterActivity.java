package com.yiyiba.photo.ui.activity;

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

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import es.dmoral.toasty.Toasty;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_register_username;
    private EditText et_register_nick;
    private EditText et_register_password;
    private Button btn_register;
    private ImageView iv_back;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }


    private void initView() {
        et_register_username = (EditText) findViewById(R.id.et_register_username);
        et_register_nick = (EditText) findViewById(R.id.et_register_nick);
        et_register_password = (EditText) findViewById(R.id.et_register_password);
        btn_register = (Button) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(this);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("注册");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                submit();
                break;
            case R.id.iv_back:
                finish();
                break;
                default:break;
        }
    }

    private void submit() {
        // validate
        String username = et_register_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toasty.warning(RegisterActivity.this, "请输入手机号", Toast.LENGTH_SHORT, true).show();
            return;
        }

        String password = et_register_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toasty.warning(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT, true).show();
            return;
        }

        String nick = et_register_nick.getText().toString().trim();
        if (TextUtils.isEmpty(nick)) {
            Toasty.warning(RegisterActivity.this, "请输入昵称", Toast.LENGTH_SHORT, true).show();
            return;
        }

        //开始执行注册
        signUp(username, password, nick);

    }

    /**
     * 账号密码注册
     */
    private void signUp(String username, String password, String nick) {
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNick(nick);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    Toasty.success(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT, true).show();
                    finish();
                } else {
                    Toasty.error(RegisterActivity.this, "用户名或已存在!", Toast.LENGTH_SHORT, true).show();
                }
            }
        });
    }
}
