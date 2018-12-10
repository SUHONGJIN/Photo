package com.yiyiba.photo.ui.fragment.photofragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yiyiba.photo.R;

/**
 * Created by SuHongJin on 2018/12/9.
 */

public class Fragment1 extends Fragment {
    private RecyclerView mRecyclerView;
    private ImageView mImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        mImageView = (ImageView) view.findViewById(R.id.mImageView);
        Glide.with(getContext()).load("http://p0.so.qhmsg.com/bdr/1080__/t019706030483cc8cbf.jpg").into(mImageView);
    }
}
