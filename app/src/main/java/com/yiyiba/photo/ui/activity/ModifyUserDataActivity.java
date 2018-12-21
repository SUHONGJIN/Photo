package com.yiyiba.photo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yiyiba.photo.R;
import com.yiyiba.photo.bean.User;
import com.yiyiba.photo.common.BaseActivity;
import com.yiyiba.photo.utlis.ActivityCollector;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import es.dmoral.toasty.Toasty;

public class ModifyUserDataActivity extends BaseActivity implements View.OnClickListener {
    private static final int TYPE_VALUE_NICK = 1;
    private static final int TYPE_VALUE_PASSWORD = 2;
    private ImageView iv_back;
    private TextView tv_title;
    private EditText et_nick;
    private LinearLayout ll_modify_nick;
    private EditText et_password_old;
    private EditText et_password_new;
    private LinearLayout ll_modify_password;
    private Button btn_commit_modify;
    private int type;
    private static final int CODE_UPDATE_NICK_SUCCESS = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user_data);
        initView();
        initData();
    }

    private void initData() {
        //获取用户昵称
        User user = BmobUser.getCurrentUser(User.class);
        String nick =user.getNick();
        if (nick!=null){
            et_nick.setText(nick);
            //显示光标位置在最后
            et_nick.setSelection(nick.length());
        }

    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        et_nick = (EditText) findViewById(R.id.et_nick);
        ll_modify_nick = (LinearLayout) findViewById(R.id.ll_modify_nick);
        et_password_old = (EditText) findViewById(R.id.et_password_old);
        et_password_new = (EditText) findViewById(R.id.et_password_new);
        ll_modify_password = (LinearLayout) findViewById(R.id.ll_modify_password);
        btn_commit_modify = (Button) findViewById(R.id.btn_commit_modify);
        btn_commit_modify.setOnClickListener(this);
        iv_back.setOnClickListener(this);

        String title = getIntent().getStringExtra("title");
        type = getIntent().getIntExtra("type",1);
        if (title!=null){
            tv_title.setText(title);
        }
        if (type==TYPE_VALUE_NICK){
            ll_modify_nick.setVisibility(View.VISIBLE);
            return;
        }
        if (type==TYPE_VALUE_PASSWORD){
            ll_modify_password.setVisibility(View.VISIBLE);
            return;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit_modify:
                if (type==TYPE_VALUE_NICK){
                    submitNick();
                    return;
                }
                if (type==TYPE_VALUE_PASSWORD){
                    submitPassword();
                    return;
                }
                break;
            case R.id.iv_back:
                finish();
                break;
                default:break;
        }
    }

    private void submitNick() {

        String nick = et_nick.getText().toString().trim();
        if (TextUtils.isEmpty(nick)) {
            Toasty.warning(ModifyUserDataActivity.this, "请输入昵称", Toast.LENGTH_SHORT, true).show();
            return;
        }

        updateNick(nick);
    }
    private void submitPassword() {

        String password_old = et_password_old.getText().toString().trim();
        if (TextUtils.isEmpty(password_old)) {
            Toasty.warning(ModifyUserDataActivity.this, "请输入旧密码", Toast.LENGTH_SHORT, true).show();
            return;
        }

        String password_new = et_password_new.getText().toString().trim();
        if (TextUtils.isEmpty(password_new)) {
            Toasty.warning(ModifyUserDataActivity.this, "请输入新密码", Toast.LENGTH_SHORT, true).show();
            return;
        }

        // TODO validate success, do something
        updatePassword(password_old, password_new);
    }

    /**
     * 更新用户昵称并同步更新本地的用户信息
     */
    private void updateNick(String nick) {
        final User user = BmobUser.getCurrentUser(User.class);
        user.setNick(nick);
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toasty.success(ModifyUserDataActivity.this, "昵称修改成功", Toast.LENGTH_SHORT, true).show();
                    setResult(CODE_UPDATE_NICK_SUCCESS);
                    finish();
                } else {
                    Toasty.error(ModifyUserDataActivity.this, "昵称修改失败。", Toast.LENGTH_SHORT, true).show();
                }
            }
        });
    }

    /**
     * 提供旧密码修改密码
     */
    private void updatePassword(String oldPwd, String newPwd) {
        //TODO 此处替换为你的旧密码和新密码
        BmobUser.updateCurrentUserPassword(oldPwd, newPwd, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toasty.success(ModifyUserDataActivity.this, "密码修改成功，请重新登录。", Toast.LENGTH_SHORT, true).show();
                    ActivityCollector.removeAllActivity();
                    BmobUser.logOut();
                    startActivity(new Intent(ModifyUserDataActivity.this,LoginActivity.class));
                } else {
                    Toasty.error(ModifyUserDataActivity.this, "密码修改失败。", Toast.LENGTH_SHORT, true).show();
                }
            }
        });
    }
}
