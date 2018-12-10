package com.yiyiba.photo.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yiyiba.photo.utlis.ActivityCollector;

/**
 * Created by SuHongJin on 2018/12/9.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        //添加activity
        ActivityCollector.addActivity(this);
//        //返回
//        ImageView imageView = (ImageView)this.findViewById(R.id.iv_back);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
    }

    //Activity销毁时，移除活动管理类中集合里的activity
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
