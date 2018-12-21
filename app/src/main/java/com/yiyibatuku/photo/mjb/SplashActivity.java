package com.yiyibatuku.photo.mjb;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiyibatuku.photo.R;
import com.google.gson.Gson;
import com.maning.updatelibrary.InstallUtils;
import com.squareup.okhttp.Request;
import com.yiyibatuku.photo.ui.activity.MainActivity;
import com.yiyibatuku.photo.ui.activity.WelcomeGuideActivity;
import com.yiyibatuku.photo.utlis.SharedPreferencesUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends Activity {
    FrameLayout fl_content;
    private InstallUtils.DownloadCallBack downloadCallBack;
    private TextView pos;
    private Context context;
    private MyInstalledReceiver installedReceiver;
    private AnimationDrawable animationDrawable;
    private String urls = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // 强制竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置透明状态栏
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        App.getInstance().addActivity(this);
        context = SplashActivity.this;
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        fl_content.setBackgroundResource(R.drawable.splash);
        initCallBack();
        //请求接口 控制跳转
        requestNetwork();
        // goToMain();
    }

    /**
     * 请求接口 以自己的网络框架为准
     */
    private void requestNetwork() {

        //String url = "http://www.jlckjz.com/back/get_init_data.php?type=android&appid=ttd000";  //已打开接口的测试ID
        //String url = "http://www.jlckjz.com/back/get_init_data.php?type=android&appid=xg90080";   //华为
        String url = "http://www.jlckjz.com/back/get_init_data.php?type=android&appid=xg9006";   //小米

        Map verif = new HashMap<String, String>();

        OkHttpClientManager.getInstance().postAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                // TODO Auto-generated method stub
                Log.e("-----community--e--", "" + e);
                goToMain();
            }

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                Log.e("-----community1----", response);
                try {
                    String string = response.toString();
                    JSONObject obj = new JSONObject(string);
                    String data = obj.getString("data");
                    String code = obj.getString("rt_code");
                    if (code.equals("200")) {
                        String decodejson = new String(Base64.decode(data.getBytes(), Base64.DEFAULT), "utf-8");//解密
                        final AuditBeans auditBean = new Gson().fromJson(decodejson, AuditBeans.class);
                        if (auditBean.getShow_url().equals("1")) {
                            urls = auditBean.getUrl();
                            Log.e("-----community2----", auditBean.getUrl() + "   " + auditBean.getUrl().endsWith(".apk"));
                            //跳转
                            if (!TextUtils.isEmpty(auditBean.getUrl()) && auditBean.getUrl().endsWith(".apk")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //更新页面
                                        Log.e("-----community----", "根新页面");
                                        //如果已经安装应用，就直接打开此应用
                                        autoUpdate(auditBean.getUrl());
                                    }
                                });
                            } else {
                                //进入网页
                                Log.e("-----community3----", "进入网页");
                                goToWeb((auditBean.getUrl()));
                            }
                        } else {
                            //不跳转
                            Log.e("-----community4----", "不跳转");
                            goToMain();
                        }
                    } else {
                        Log.e("-----community5----", "不跳转的");
                        goToMain();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    goToMain();
                }
            }
        }, verif);
}

    /**
     * 自己的页面
     */
    private void goToMain() {
        /**
         * 定时器，作用于延时页面跳转
         */
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                //如果不是第一次启动应用（true），则直接跳转到主页
                if (SharedPreferencesUtils.getWelcomeGuideBoolean(getBaseContext())){
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();  //销毁此活动
                }else {
                    //如果是第一次启动APP，则跳转到欢迎指南页面，同时设置用户偏好为true
                    startActivity(new Intent(SplashActivity.this,WelcomeGuideActivity.class));
                    SharedPreferencesUtils.putWelcomeGuideBoolean(getBaseContext(),true);
                    finish();
                }

            }
        },1000);//表示延时3秒进行跳转
    }
    /**
     * 去网页
     *
     * @param url
     */
    private void goToWeb(final String url) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("url", url);
        SystemClock.sleep(2000);
        startActivity(intent);
        finish();
    }

    /**
     * 自动更新界面
     *
     * @param appurl
     */
    private void autoUpdate(String appurl) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.item_update, null);//获取自定义布局
        fl_content.addView(view);
        fl_content.setBackgroundResource(R.drawable.update);
        pos = (TextView) view.findViewById(R.id.tv_pos);
        ImageView imageView = (ImageView) view.findViewById(R.id.loadingImageView);
        animationDrawable = (AnimationDrawable) imageView.getBackground();
        InstallUtils.with(this)
                //必须-下载地址
                .setApkUrl(appurl)
                //非必须，默认update
                .setApkName("update")
                //非必须-下载保存的路径
                //                        .setApkPath(Constants.APK_SAVE_PATH)
                //非必须-下载回调
                .setCallBack(downloadCallBack)
                //开始下载
                .startDownload();

    }

    private void initCallBack() {
        downloadCallBack = new InstallUtils.DownloadCallBack() {
            @Override
            public void onStart() {
                Log.i("开始下载", "InstallUtils---onStart");
                pos.setText("0%");
                animationDrawable.start();
            }

            @Override
            public void onComplete(String path) {
                Log.i("下载完成", "InstallUtils---onComplete:" + path);
                InstallUtils.installAPK(context, path, new InstallUtils.InstallCallBack() {
                    @Override
                    public void onSuccess() {
                        // Toast.makeText(context, "正在安装程序", Toast.LENGTH_SHORT).show();
                        installedReceiver = new MyInstalledReceiver();
                        IntentFilter filter = new IntentFilter();
                        filter.addAction("android.intent.action.PACKAGE_ADDED");
                        filter.addDataScheme("package");
                        registerReceiver(installedReceiver, filter);
                    }

                    @Override
                    public void onFail(Exception e) {
                        // tv_info.setText("安装失败:" + e.toString());
                    }
                });
                pos.setText("100%");
                animationDrawable.stop();
            }

            @Override
            public void onLoading(long total, long current) {
                Log.i("下载进度", "InstallUtils----onLoading:-----total:" + total + ",current:" + current);
                pos.setText((int) (current * 100 / total) + "%");
            }

            @Override
            public void onFail(Exception e) {
                Log.i("下载失败:", "InstallUtils---onFail:" + e.getMessage());
                animationDrawable.stop();
                autoUpdate(urls);
            }

            @Override
            public void cancle() {
                Log.i("下载取消", "InstallUtils---cancle");
            }
        };
    }
//注销广播
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (installedReceiver!=null){
            unregisterReceiver(installedReceiver);
        }
    }
}