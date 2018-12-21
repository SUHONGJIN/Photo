package com.yiyibatuku.photo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yiyibatuku.photo.R;
import com.yiyibatuku.photo.bean.FeedBack;
import com.yiyibatuku.photo.bean.User;
import com.yiyibatuku.photo.common.BaseActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import es.dmoral.toasty.Toasty;

public class FeedBackActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_title;
    private EditText et_feedback;
    private Button btn_commit_feedback;
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        initView();
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        et_feedback = (EditText) findViewById(R.id.et_feedback);
        btn_commit_feedback = (Button) findViewById(R.id.btn_commit_feedback);
        tv_title.setText("吐槽与建议");
        btn_commit_feedback.setOnClickListener(this);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit_feedback:
                submit();
                break;
            case R.id.iv_back:
                finish();
                break;default:
        }
    }

    private void submit() {
        // validate
        String feedback = et_feedback.getText().toString().trim();

        if (!BmobUser.isLogin()){
            Toasty.info(FeedBackActivity.this, "请先登录再反馈", Toast.LENGTH_SHORT, true).show();
            startActivity(new Intent(FeedBackActivity.this,LoginActivity.class));
            return;
        }

        if (TextUtils.isEmpty(feedback)) {
            Toasty.warning(FeedBackActivity.this, "请输入要吐槽和建议的内容！", Toast.LENGTH_SHORT, true).show();
            return;
        }
        if (BmobUser.isLogin()) {
            User user = BmobUser.getCurrentUser(User.class);
            String username = user.getUsername();
            String nick = user.getNick();
            String content = et_feedback.getText().toString();
            setFeedBack(username,nick,content);
        }
    }

    /**
     * 用户提交反馈信息
     * @param username   用户名
     * @param nick   用户昵称
     * @param content   用户反馈内容
     */
    private void setFeedBack(String username,String nick,String content) {

        FeedBack feedBack = new FeedBack();
        feedBack.setUsername(username);
        feedBack.setNick(nick);
        feedBack.setContent(content);
        feedBack.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){
                    Toasty.success(FeedBackActivity.this,"反馈成功！",Toast.LENGTH_SHORT, true).show();
                    finish();
                }else {
                    Toasty.error(FeedBackActivity.this, "反馈失败，请检查网络!", Toast.LENGTH_SHORT, true).show();
                }
            }
        });
    }
}
