package com.yiyiba.photo.ui.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.longsh.optionframelibrary.OptionMaterialDialog;
import com.yiyiba.photo.R;
import com.yiyiba.photo.common.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.sharesdk.onekeyshare.OnekeyShare;
import es.dmoral.toasty.Toasty;

public class ShowImageActivity extends BaseActivity implements View.OnClickListener {


    private PhotoView iv_show_image;
    private ImageView iv_back;
    private LinearLayout ll_load_state;
    private ImageView iv_like;
    private ImageView iv_download_image;
    private String image_url;
    private String image_title;
    private ProgressBar mProgress;
    private int progress = 0;
    private TextView tv_progress_value;
    private ImageView iv_share_image;
    private String imageSavePath;
    //创建权限集合
    private List<String> permissionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        initView();
        initData();
        checkPermission();
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
        iv_share_image = (ImageView) findViewById(R.id.iv_share_image);
        iv_share_image.setOnClickListener(this);
    }

    private void initData() {
        image_url = getIntent().getStringExtra("image_url");
        image_title = getIntent().getStringExtra("image_title");
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
                    Toast.makeText(ShowImageActivity.this, "点赞成功", Toast.LENGTH_SHORT).show();
                } else if (iv_like.getTag().equals("select")) {
                    iv_like.setImageResource(R.mipmap.icon_like_default);
                    iv_like.setTag("unselect");
                    Toast.makeText(ShowImageActivity.this, "取消点赞成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_download_image:

                //获取当前系统时间
                String fileName = String.valueOf(System.currentTimeMillis());
                BmobFile bmobfile = new BmobFile("myimage" + fileName + ".png", "", image_url);
                downloadFile(bmobfile);
                break;
            case R.id.iv_share_image:

                if (imageSavePath==null){
                    final OptionMaterialDialog mMaterialDialog = new OptionMaterialDialog(ShowImageActivity.this);
                    mMaterialDialog.setTitle("小贴士：")
                            .setMessage("保存图片至本地才能分享哦")
                            .setPositiveButton("保存", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //获取当前系统时间
                                    String fileName2 = String.valueOf(System.currentTimeMillis());
                                    BmobFile bmobfile2 = new BmobFile("myimage" + fileName2 + ".png", "", image_url);
                                    downloadFile(bmobfile2);
                                    mMaterialDialog.dismiss();
                                }
                            })
                            .setNegativeButton("取消",
                                    new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            mMaterialDialog.dismiss();
                                        }
                                    })
                            .setCanceledOnTouchOutside(true)
                            .show();
                }else {
                    showShare();
                }
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
                    imageSavePath=savePath;
                    mProgress.setVisibility(View.GONE);
                    tv_progress_value.setVisibility(View.GONE);
                    iv_download_image.setVisibility(View.VISIBLE);
                    progress = 0;
                    Toasty.success(ShowImageActivity.this, "图片已保存至本地", Toast.LENGTH_SHORT, true).show();
                } else {
                    Log.i("bmoba", "下载失败：" + e.getErrorCode() + "," + e.getMessage());
                    mProgress.setVisibility(View.GONE);
                    tv_progress_value.setVisibility(View.GONE);
                    iv_download_image.setVisibility(View.VISIBLE);
                    progress = 0;
                    Toasty.error(ShowImageActivity.this, "下载失败,请检查权限或网络是否开启。", Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onProgress(Integer value, long newworkSpeed) {
                Log.i("bmoba", "下载进度：" + value + "," + newworkSpeed);
                progress = value;
                mProgress.setProgress(progress);
                tv_progress_value.setText(progress + "%");
            }

        });
    }
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("图片作品分享");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl(image_url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(image_title);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(imageSavePath);//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl(image_url);
        // 启动分享GUI
        oks.show(this);
    }
    //
    private void checkPermission() {
        //检查权限是否获取
        if (ContextCompat.checkSelfPermission(ShowImageActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(ShowImageActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(android.Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (!permissionList.isEmpty()) {
            String[] permission = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(ShowImageActivity.this, permission, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int results : grantResults) {
                        if (results != PackageManager.PERMISSION_GRANTED) {
                            Toasty.warning(ShowImageActivity.this, "为了能正常下载保存图片，建议打开相应的权限。");
                        } else {
                            //TODO
                        }
                    }
                } else {
                    Toasty.error(ShowImageActivity.this, "未知权限错误!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
