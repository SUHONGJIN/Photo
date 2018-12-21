package com.yiyibatuku.photo.ui.fragment.mainfragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.yiyibatuku.photo.R;
import com.yiyibatuku.photo.bean.Bing;
import com.yiyibatuku.photo.common.HttpUrl;
import com.yiyibatuku.photo.ui.activity.PhotoListActivity;
import com.yiyibatuku.photo.ui.activity.ShowImageActivity;
import com.yiyibatuku.photo.utlis.HttpUtil;
import com.yiyibatuku.photo.view.ClassifyItemView;

import java.io.IOException;

import es.dmoral.toasty.Toasty;

/**
 * Created by SuHongJin on 2018/12/9.
 */

public class ClassifyFragment extends Fragment implements View.OnClickListener {
    private ImageView iv_everyday_image;
    private SmartRefreshLayout mRefreshLayout;
    private TextView tv_bing_title;
    private TextView tv_bing_description;
    private String imgPath;
    private String title;
    private String description;
    private CardView cv_bing_image;
    private ClassifyItemView classify_item1;
    private ClassifyItemView classify_item2;
    private ClassifyItemView classify_item3;
    private ClassifyItemView classify_item4;
    private ClassifyItemView classify_item5;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Toasty.error(getContext(), "获取图片失败", Toast.LENGTH_SHORT, true).show();
                    break;
                case 1:
                    Glide.with(getContext()).load("http://api.mmno.com/api/bing/img_1366").into(iv_everyday_image);
                    tv_bing_title.setText(title);
                    tv_bing_description.setText(description);
                    break;
                default:
                    break;
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classify, container, false);
        initView(view);
        initData();
        cv_bing_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ShowImageActivity.class);
                intent.putExtra("image_url", imgPath);
                intent.putExtra("image_title", title);
                startActivity(intent);
            }
        });
        return view;
    }

    private void initData() {
        HttpUtil.sendOkHttpRequest(HttpUrl.BING_URL, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Message message = new Message();
                message.what = 0;
                mHandler.sendMessage(message);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String data = response.body().string();
                setData(data);
                //Log.i("tag1",data);
            }

        });
    }


    private void initView(View view) {

        iv_everyday_image = (ImageView) view.findViewById(R.id.iv_everyday_image);
        mRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.mRefreshLayout);
        tv_bing_title = (TextView) view.findViewById(R.id.tv_bing_title);
        tv_bing_description = (TextView) view.findViewById(R.id.tv_bing_description);
        cv_bing_image = (CardView) view.findViewById(R.id.cv_bing_image);

        //刷新数据
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                initData();
                mRefreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });

        classify_item1 = (ClassifyItemView) view.findViewById(R.id.classify_item1);
        classify_item1.setOnClickListener(this);
        classify_item2 = (ClassifyItemView) view.findViewById(R.id.classify_item2);
        classify_item2.setOnClickListener(this);
        classify_item3 = (ClassifyItemView) view.findViewById(R.id.classify_item3);
        classify_item3.setOnClickListener(this);
        classify_item4 = (ClassifyItemView) view.findViewById(R.id.classify_item4);
        classify_item4.setOnClickListener(this);
        classify_item5 = (ClassifyItemView) view.findViewById(R.id.classify_item5);
        classify_item5.setOnClickListener(this);
    }

    private void setData(String data) {
        Gson gson = new Gson();
        Bing bing = gson.fromJson(data, Bing.class);

        title = bing.getShowapi_res_body().getData().getTitle();
        description = bing.getShowapi_res_body().getData().getDescription();
        imgPath = bing.getShowapi_res_body().getData().getImg_1366();

        Message message = new Message();
        message.what = 1;
        mHandler.sendMessage(message);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.classify_item1:
                Intent intent = new Intent(getContext(), PhotoListActivity.class);
                intent.putExtra("title","宠物拍摄");
                intent.putExtra("tag","cw");
                startActivity(intent);
                break;
            case R.id.classify_item2:
                Intent intent2 = new Intent(getContext(),PhotoListActivity.class);
                intent2.putExtra("title","随拍作品");
                intent2.putExtra("tag","et");
                startActivity(intent2);
                break;
            case R.id.classify_item3:
                Intent intent3 = new Intent(getContext(),PhotoListActivity.class);
                intent3.putExtra("title","人像拍摄");
                intent3.putExtra("tag","rx");
                startActivity(intent3);
                break;
            case R.id.classify_item4:
                Intent intent4 = new Intent(getContext(),PhotoListActivity.class);
                intent4.putExtra("title","手机拍摄");
                intent4.putExtra("tag","sj");
                startActivity(intent4);
                break;
            case R.id.classify_item5:
                Intent intent5 = new Intent(getContext(),PhotoListActivity.class);
                intent5.putExtra("title","其他作品");
                intent5.putExtra("tag","qt");
                startActivity(intent5);
                break;
            default:break;
        }
    }
}
