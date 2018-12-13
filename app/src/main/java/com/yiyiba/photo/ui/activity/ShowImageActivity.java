package com.yiyiba.photo.ui.activity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.yiyiba.photo.R;
import com.yiyiba.photo.common.BaseActivity;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import es.dmoral.toasty.Toasty;

public class ShowImageActivity extends BaseActivity implements View.OnClickListener {


    private PhotoView iv_show_image;
    private ImageView iv_back;
    private LinearLayout ll_load_state;
    private ImageView iv_like;
    private ImageView iv_download_image;
    private String image_url;
    private ProgressBar mProgress;
    private int progress = 0;
    private TextView tv_progress_value;

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

        iv_like = (ImageView) findViewById(R.id.iv_like);
        iv_like.setOnClickListener(this);
        iv_download_image = (ImageView) findViewById(R.id.iv_download_image);
        iv_download_image.setOnClickListener(this);
        mProgress = (ProgressBar) findViewById(R.id.mProgress);
        tv_progress_value = (TextView) findViewById(R.id.tv_progress_value);
    }

    private void initData() {
        image_url = getIntent().getStringExtra("image_url");
        Glide.with(ShowImageActivity.this).load(image_url).into(iv_show_image);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_like:
                //通过判断标签来实现取消和选中图标
                if (iv_like.getTag().equals("unselect")) {
                    iv_like.setImageResource(R.mipmap.icon_like_select);
                    iv_like.setTag("select");
                    Toast.makeText(ShowImageActivity.this,"点赞成功",Toast.LENGTH_SHORT).show();
                } else if (iv_like.getTag().equals("select")) {
                    iv_like.setImageResource(R.mipmap.icon_like_default);
                    iv_like.setTag("unselect");
                    Toast.makeText(ShowImageActivity.this,"点赞取消",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_download_image:
                //获取当前系统时间
                String fileName = String.valueOf(System.currentTimeMillis());
                BmobFile bmobfile = new BmobFile("myimage" + fileName + ".png", "", image_url);
                downloadFile(bmobfile);
                break;
            default:
                break;
        }
    }

    private void downloadFile(BmobFile file) {
        //允许设置下载文件的存储路径，默认下载文件的目录为：context.getApplicationContext().getCacheDir()+"/bmob/"
        File saveFile = new File(Environment.getExternalStorageDirectory(), file.getFilename());
        file.download(saveFile, new DownloadFileListener() {

            @Override
            public void onStart() {
                Log.i("bmoba", "开始下载...");
                iv_download_image.setVisibility(View.GONE);
                tv_progress_value.setVisibility(View.VISIBLE);
                mProgress.setVisibility(View.VISIBLE);
                tv_progress_value.setText("0%");
                mProgress.setProgress(progress);
            }

            @Override
            public void done(String savePath, BmobException e) {
                if (e == null) {
                    Log.i("bmoba", "下载成功,保存路径:" + savePath);
                    Toasty.success(ShowImageActivity.this, "下载成功", Toast.LENGTH_SHORT, true).show();
                } else {
                    Log.i("bmoba", "下载失败：" + e.getErrorCode() + "," + e.getMessage());
                    Toasty.error(ShowImageActivity.this, "下载失败", Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onProgress(Integer value, long newworkSpeed) {
                Log.i("bmoba", "下载进度：" + value + "," + newworkSpeed);
                progress = value;
                mProgress.setProgress(progress);
                tv_progress_value.setText(progress + "%");
                if (progress == 100) {
                    mProgress.setVisibility(View.GONE);
                    tv_progress_value.setVisibility(View.GONE);
                    iv_download_image.setVisibility(View.VISIBLE);
                    progress=0;
                }
            }

        });
    }

}
