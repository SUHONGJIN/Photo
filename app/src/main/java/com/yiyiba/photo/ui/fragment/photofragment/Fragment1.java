package com.yiyiba.photo.ui.fragment.photofragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yiyiba.photo.R;
import com.yiyiba.photo.adapter.Photo1Adapter;
import com.yiyiba.photo.bean.Photo1;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by SuHongJin on 2018/12/9.
 */

public class Fragment1 extends Fragment {
    private RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, container, false);

        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

    }
    private void initData() {
        //只返回Person表的objectId这列的值
        BmobQuery<Photo1> bmobQuery = new BmobQuery<Photo1>();
        bmobQuery.addQueryKeys("imageUrl,imageTitle");
        bmobQuery.findObjects(new FindListener<Photo1>() {
            @Override
            public void done(List<Photo1> object, BmobException e) {
                if(e==null){
                    Photo1Adapter adapter = new Photo1Adapter(object,getContext());
                    mRecyclerView.setAdapter(adapter);
                }else{

                }
            }
        });

    }


}
