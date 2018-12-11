package com.yiyiba.photo.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.yiyiba.photo.R;
import com.yiyiba.photo.common.BaseActivity;

public class ShowImageActivity extends BaseActivity {


    private PhotoView iv_show_image;
    private ImageView iv_back;
    private LinearLayout ll_load_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        initView();
        initData();
    }


    private void initView() {
        iv_show_image = (PhotoView) findViewById(R.id.iv_show_image);
        ll_load_state = (LinearLayout) findViewById(R.id.ll_load_state);
        iv_show_image.enable();  //图片可以旋转缩放
        //返回按钮
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void initData() {
        String image_url = getIntent().getStringExtra("image_url");
        Glide.with(ShowImageActivity.this).load(image_url).into(iv_show_image);
    }

}
