package com.yiyiba.photo.ui.fragment.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiyiba.photo.R;
import com.yiyiba.photo.ui.fragment.photofragment.Fragment1;
import com.yiyiba.photo.ui.fragment.photofragment.Fragment2;
import com.yiyiba.photo.ui.fragment.photofragment.Fragment3;
import com.yiyiba.photo.ui.fragment.photofragment.Fragment4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuHongJin on 2018/12/9.
 */

public class PhotoFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<String> titles;
    private List<Fragment> fragments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTabLayout = (TabLayout) view.findViewById(R.id.mTabLayout);
        mViewPager = (ViewPager) view.findViewById(R.id.mViewPager);

        //标题集合
        titles=new ArrayList<>();
        titles.add("风光");
        titles.add("旅行");
        titles.add("纪实");
        titles.add("生活");
        //碎片集合
        fragments = new ArrayList<>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
        //适配器
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(adapter);
        //标题绑定碎片
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private class MyViewPagerAdapter extends FragmentPagerAdapter{

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //获取碎片
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        //获取碎片总数
        @Override
        public int getCount() {
            return fragments.size();
        }

        //获取标题
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}


