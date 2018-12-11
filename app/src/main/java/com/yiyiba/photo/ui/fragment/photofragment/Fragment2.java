package com.yiyiba.photo.ui.fragment.photofragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiyiba.photo.R;
import com.yiyiba.photo.adapter.Photo1Adapter;
import com.yiyiba.photo.adapter.Photo2Adapter;
import com.yiyiba.photo.bean.Photo1;
import com.yiyiba.photo.bean.Photo2;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by SuHongJin on 2018/12/9.
 */

public class Fragment2 extends Fragment {
    private RecyclerView mRecyclerView2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_layout,container,false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mRecyclerView2 = (RecyclerView) view.findViewById(R.id.mRecyclerView2);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView2.setLayoutManager(layoutManager);

    }
    private void initData() {
        //只返回Person表的objectId这列的值
        BmobQuery<Photo2> bmobQuery = new BmobQuery<Photo2>();
        bmobQuery.addQueryKeys("imageUrl2,imageTitle2");
        bmobQuery.findObjects(new FindListener<Photo2>() {
            @Override
            public void done(List<Photo2> object, BmobException e) {
                if(e==null){
                    Photo2Adapter adapter = new Photo2Adapter(object,getContext());
                    mRecyclerView2.setAdapter(adapter);
                }else{

                }
            }
        });

    }

}