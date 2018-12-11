package com.yiyiba.photo.ui.fragment.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yiyiba.photo.R;

/**
 * Created by SuHongJin on 2018/12/9.
 */

public class ClassifyFragment extends Fragment {
    private ImageView iv_everyday_image;

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
    }
}
