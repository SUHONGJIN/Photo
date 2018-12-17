package com.yiyiba.photo.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.longsh.optionframelibrary.OptionBottomDialog;
import com.yiyiba.photo.R;
import com.yiyiba.photo.bean.User;
import com.yiyiba.photo.common.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class UserInfoActivity extends BaseActivity {

    private ImageView iv_back;
    private TextView tv_title;
    private CircleImageView cv_user_head;
    private RelativeLayout rl_modify_user_head;
    private TextView tv_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initView();
        initData();
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("个人信息");
        cv_user_head = (CircleImageView) findViewById(R.id.cv_user_head);
        rl_modify_user_head = (RelativeLayout) findViewById(R.id.rl_modify_user_head);
        tv_username = (TextView) findViewById(R.id.tv_username);

        //用户头像
        String url = "http://api.mmno.com/api/bing/img_1366";
        Glide.with(UserInfoActivity.this).load(url).error(R.mipmap.icon_user_head).into(cv_user_head);

        //显示用户ID
        if (BmobUser.isLogin()) {
            User user = BmobUser.getCurrentUser(User.class);
            if (user.getUsername()!=null){
                tv_username.setText(user.getUsername());
            }
        }

    }

    private void initData() {
        rl_modify_user_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> stringList = new ArrayList<String>();
                stringList.add("拍照");
                stringList.add("从相册选择");
                final OptionBottomDialog optionBottomDialog = new OptionBottomDialog(UserInfoActivity.this, stringList);
                optionBottomDialog.setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                Toasty.info(UserInfoActivity.this, "拍照", Toast.LENGTH_SHORT, true).show();
                                break;
                            case 1:
                                Toasty.info(UserInfoActivity.this, "从相册选择", Toast.LENGTH_SHORT, true).show();
                                break;
                            default:
                                break;
                        }
                        optionBottomDialog.dismiss();
                    }
                });
            }
        });
        //返回
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
