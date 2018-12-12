package com.yiyiba.photo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.yiyiba.photo.R;
import com.yiyiba.photo.common.BaseActivity;

import cn.bmob.v3.BmobUser;
import es.dmoral.toasty.Toasty;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_back;
    private TextView tv_title;
    private RelativeLayout rl_setting_item1;
    private RelativeLayout rl_setting_item2;
    private RelativeLayout rl_setting_item3;
    private RelativeLayout rl_setting_item4;
    private RelativeLayout rl_setting_item5;
    private RelativeLayout rl_setting_item6;
    private Switch mSwitch;
    private static final int TYPE_VALUE_NICK = 1;
    private static final int TYPE_VALUE_PASSWORD =2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("设置");
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rl_setting_item1 = (RelativeLayout) findViewById(R.id.rl_setting_item1);
        rl_setting_item1.setOnClickListener(this);
        rl_setting_item2 = (RelativeLayout) findViewById(R.id.rl_setting_item2);
        rl_setting_item2.setOnClickListener(this);
        rl_setting_item3 = (RelativeLayout) findViewById(R.id.rl_setting_item3);
        rl_setting_item3.setOnClickListener(this);
        rl_setting_item4 = (RelativeLayout) findViewById(R.id.rl_setting_item4);
        rl_setting_item4.setOnClickListener(this);
        rl_setting_item5 = (RelativeLayout) findViewById(R.id.rl_setting_item5);
        rl_setting_item5.setOnClickListener(this);
        rl_setting_item6 = (RelativeLayout) findViewById(R.id.rl_setting_item6);
        rl_setting_item6.setOnClickListener(this);
        mSwitch = (Switch) findViewById(R.id.mSwitch);
        mSwitch.setOnClickListener(this);

        if (!BmobUser.isLogin()){
            rl_setting_item1.setVisibility(View.GONE);
            rl_setting_item2.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_setting_item1:
                Intent intent = new Intent(SettingActivity.this,ModifyUserDataActivity.class);
                intent.putExtra("title","修改昵称");
                intent.putExtra("type",TYPE_VALUE_NICK);
                startActivity(intent);
                break;
            case R.id.rl_setting_item2:
                Intent intent1 = new Intent(SettingActivity.this,ModifyUserDataActivity.class);
                intent1.putExtra("title","修改密码");
                intent1.putExtra("type",TYPE_VALUE_PASSWORD);
                startActivity(intent1);
                break;
            case R.id.rl_setting_item3:
                Toasty.success(SettingActivity.this, "暂时无下载内容", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_setting_item4:
                boolean isChecked = mSwitch.isChecked();
                if (isChecked){
                    mSwitch.setChecked(false);
                }else {
                    mSwitch.setChecked(true);
                }
                break;
            case R.id.rl_setting_item5:
                Toasty.success(SettingActivity.this, "清除缓存成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_setting_item6:
                Toasty.success(SettingActivity.this, "当前是最新版本", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }


}
