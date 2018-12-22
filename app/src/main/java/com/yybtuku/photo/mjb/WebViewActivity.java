package com.yybtuku.photo.mjb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends Activity {
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;


    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);
        setContentView(webView);
        WebSettings webSettings = webView.getSettings();

        // 支持javascript
        webSettings.setJavaScriptEnabled(true);

        // 支持使用localStorage(H5页面的支持)
        webSettings.setDomStorageEnabled(true);

        // 支持数据库
        webSettings.setDatabaseEnabled(true);

        // 支持缓存
        webSettings.setAppCacheEnabled(true);
        String appCaceDir = this.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        webSettings.setAppCachePath(appCaceDir);

        // 设置可以支持缩放
        webSettings.setUseWideViewPort(true);

        // 扩大比例的缩放
        webSettings.setSupportZoom(true);

        webSettings.setBuiltInZoomControls(true);

        // 隐藏缩放按钮
        webSettings.setDisplayZoomControls(false);

        // 自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);

        // 隐藏滚动条
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);

        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String s1, String s2, String s3, long l) {
                downloadByBrowser(s);
            }
        });

        // 处理网页内的连接（自身打开）
        webView.setWebViewClient(new WebViewClient() {


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

            }

            @Override
            public void onPageFinished(WebView view, String url) {

            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);

            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                // 如下方案可在非微信内部WebView的H5页面中调出微信支付
                if (url.startsWith("weixin://wap/pay?") | url.startsWith("mqqapi") | url.startsWith("alipay://")) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);

                    return true;
                } else if (parseScheme(url)) {
                    try {
                        Intent intent;
                        intent = Intent.parseUri(url,
                                Intent.URI_INTENT_SCHEME);
                        intent.addCategory("android.intent.category.BROWSABLE");
                        intent.setComponent(null);
                        // intent.setSelector(null);
                        startActivity(intent);
//
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }


        });

        // 使用返回键的方式防止网页重定向
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
                    if (webView.canGoBack()) {
                        webView.goBack();
                        return true;
                    } else if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                        return false;
                    } else {
                        TOUCH_TIME = System.currentTimeMillis();
                        Toast.makeText(WebViewActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
                return false;
            }
        });


        String url = getIntent().getStringExtra("url");
        if (url.contains("http://")
                || url.contains("https://")) {
            webView.loadUrl(url);

        } else {
            webView.loadUrl("http://" + url);
        }

    }


    public boolean parseScheme(String url) {

        if (url.contains("platformapi/startapp")) {
            return true;
        } else if ((Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
                && (url.contains("platformapi") && url.contains("startapp"))) {
            return true;
        } else {
            return false;
        }
    }

    private void downloadByBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

}
