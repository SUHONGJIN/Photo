package com.yiyiba.photo.ui.fragment.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyiba.photo.R;

/**
 * Created by SuHongJin on 2018/12/9.
 */

public class ClassifyFragment extends Fragment {
    private ImageView iv_everyday_image;
    private SmartRefreshLayout mRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classify, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        iv_everyday_image = (ImageView) view.findViewById(R.id.iv_everyday_image);
        Glide.with(getContext()).load("http://api.mmno.com/api/bing/img_1366").into(iv_everyday_image);

        mRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.mRefreshLayout);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {

                mRefreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
    }
}
