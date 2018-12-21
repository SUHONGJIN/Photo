package com.yiyibatuku.photo.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.longsh.optionframelibrary.OptionBottomDialog;
import com.yiyibatuku.photo.R;
import com.yiyibatuku.photo.bean.User;
import com.yiyibatuku.photo.common.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserInfoActivity extends BaseActivity {

    private ImageView iv_back;
    private TextView tv_title;
    private CircleImageView cv_user_head;
    private RelativeLayout rl_modify_user_head;
    private TextView tv_username;
    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_ALBUM = 2;
    public static final int CAMERA_PERMISSION_CODE = 5;
    private File mImageFile;
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
        String url = "http://p1.qqyou.com/touxiang/UploadPic/2014-7/25/2014072522521653329.jpg";
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
                                //Toasty.info(UserInfoActivity.this, "拍照", Toast.LENGTH_SHORT, true).show();
                                //Android6.0以上要获取动态权限
                                //先判断该页面是否已经授予拍照权限
                                if (ContextCompat.checkSelfPermission(UserInfoActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                    //获取拍照权限
                                    ActivityCompat.requestPermissions(UserInfoActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
                                } else {
                                    //拍照
                                    selectCamera();
                                }
                                break;
                            case 1:
                                //Toasty.info(UserInfoActivity.this, "从相册选择", Toast.LENGTH_SHORT, true).show();
                                //调用相册
                                selectAlbum();
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

    //选择相机
    private void selectCamera() {
        mImageFile = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri fileUri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {  //如果是7.0以上，使用FileProvider，否则会报错
            fileUri = FileProvider.getUriForFile(UserInfoActivity.this, "com.yiyibatuku.photo.fileprovider", mImageFile);
        } else {
            fileUri = Uri.fromFile(mImageFile);
        }
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(cameraIntent, REQUEST_CAMERA);
    }

    //选择相册
    private void selectAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_ALBUM);
    }

    //处理权限请求响应
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION_CODE:
                selectCamera();
                break;
        }

    }
}
