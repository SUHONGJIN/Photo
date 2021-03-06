package com.yiyiba.photo.ui.fragment.photofragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyiba.photo.R;
import com.yiyiba.photo.adapter.Photo4Adapter;
import com.yiyiba.photo.bean.Photo4;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by SuHongJin on 2018/12/9.
 */

public class Fragment4 extends Fragment {
    private RecyclerView mRecyclerView4;
    private SmartRefreshLayout refreshLayout;
    private Photo4Adapter adapter;
    private LinearLayout ll_load_state;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4_layout, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mRecyclerView4 = (RecyclerView) view.findViewById(R.id.mRecyclerView4);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        ll_load_state = (LinearLayout) view.findViewById(R.id.ll_load_state);
        mRecyclerView4.setLayoutManager(layoutManager);
        refreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
    }

    private void initData() {
        //只返回Person表的objectId这列的值
        BmobQuery<Photo4> bmobQuery = new BmobQuery<Photo4>();
        bmobQuery.addQueryKeys("imageUrl4,imageTitle4");
        bmobQuery.findObjects(new FindListener<Photo4>() {
            @Override
            public void done(List<Photo4> object, BmobException e) {
                if (e == null) {
                    adapter = new Photo4Adapter(object, getContext());
                    mRecyclerView4.setAdapter(adapter);
                    mRecyclerView4.setVisibility(View.VISIBLE);
                    ll_load_state.setVisibility(View.GONE);
                } else {

                }
            }
        });


        //下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (adapter != null) {
                    adapter.notifyDataSetChanged();  //通知适配器数据改变
                }
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        //上拉刷新
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });

    }
}
