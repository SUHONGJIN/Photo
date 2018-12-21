package com.yiyibatuku.photo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.yiyibatuku.photo.R;
import com.yiyibatuku.photo.common.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class WelcomeGuideActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager pager_guide;
    private Button btn_guide;
    private List<View> viewList = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_guide);
        initView();
        initViewPager();
    }

    private void initView() {
        pager_guide = (ViewPager) findViewById(R.id.pager_guide);
        btn_guide = (Button) findViewById(R.id.btn_guide);
        btn_guide.setOnClickListener(this);
    }

    private void initViewPager() {
        ImageView  iv1 = new ImageView(this);
        iv1.setImageResource(R.drawable.guide1);
        iv1.setScaleType(ImageView.ScaleType.FIT_XY);
        viewList.add(iv1);
        ImageView  iv2 = new ImageView(this);
        iv2.setImageResource(R.drawable.guide2);
        iv2.setScaleType(ImageView.ScaleType.FIT_XY);
        viewList.add(iv2);
        ImageView  iv3 = new ImageView(this);
        iv3.setImageResource(R.drawable.guide3);
        iv3.setScaleType(ImageView.ScaleType.FIT_XY);
        viewList.add(iv3);

        MyViewPagerAdaper adaper = new MyViewPagerAdaper();
        pager_guide.setAdapter(adaper);

        pager_guide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i==2){
                    btn_guide.setVisibility(View.VISIBLE);
                }else {
                    btn_guide.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_guide:
                startActivity(new Intent(WelcomeGuideActivity.this, MainActivity.class));
                finish();
                break;
        }
    }

    class MyViewPagerAdaper extends PagerAdapter{

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view==o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(viewList.get(position));
        }
    }
}
