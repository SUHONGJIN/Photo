package com.yiyiba.photo.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yiyiba.photo.R;
import com.yiyiba.photo.common.BaseActivity;

public class ShowImageActivity extends BaseActivity {

    private ImageView iv_show_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        initView();
    }

    private void initView() {
        iv_show_image = (ImageView) findViewById(R.id.iv_show_image);
        Glide.with(ShowImageActivity.this).load("http://www.qqoi.cn/img_meinv/158037813.jpeg").into(iv_show_image);
    }
}
