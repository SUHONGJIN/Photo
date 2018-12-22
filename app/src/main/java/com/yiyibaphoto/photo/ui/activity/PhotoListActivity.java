package com.yiyibaphoto.photo.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyibaphoto.photo.R;
import com.yiyibaphoto.photo.adapter.ChildPhotoAdapter;
import com.yiyibaphoto.photo.adapter.OtherPhotoAdapter;
import com.yiyibaphoto.photo.adapter.PeoplePhotoAdapter;
import com.yiyibaphoto.photo.adapter.PetPhotoAdapter;
import com.yiyibaphoto.photo.adapter.PhonePhotoAdapter;
import com.yiyibaphoto.photo.bean.ChildPhoto;
import com.yiyibaphoto.photo.bean.OtherPhoto;
import com.yiyibaphoto.photo.bean.PeoplePhoto;
import com.yiyibaphoto.photo.bean.PetPhoto;
import com.yiyibaphoto.photo.bean.PhonePhoto;
import com.yiyibaphoto.photo.common.BaseActivity;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class PhotoListActivity extends BaseActivity {

    private ImageView iv_back;
    private TextView tv_title;
    private RecyclerView mRecyclerView;
    private LinearLayout ll_load_state;
    private SmartRefreshLayout mSmartRefreshLayout;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list);
        initView();
        switch (tag){
            case "cw":
                initData1();
                break;
            case "et":
                initData2();
                break;
            case "rx":
                initData3();
                break;
            case "sj":
                initData4();
                break;
            case "qt":
                initData5();
                break;
        }

        //下拉刷新
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                switch (tag){
                    case "cw":
                        initData1();
                        break;
                    case "et":
                        initData2();
                        break;
                    case "rx":
                        initData3();
                        break;
                    case "sj":
                        initData4();
                        break;
                    case "qt":
                        initData5();
                        break;
                }
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        //上拉刷新
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        ll_load_state = (LinearLayout) findViewById(R.id.ll_load_state);
        String title = getIntent().getStringExtra("title");
        tag = getIntent().getStringExtra("tag");
        tv_title.setText(title);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSmartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.mSmartRefreshLayout);
    }

    private void initData1() {

        //只返回Person表的objectId这列的值
        BmobQuery<PetPhoto> bmobQuery = new BmobQuery<PetPhoto>();
        bmobQuery.addQueryKeys("imagePath,imageTitle");
        bmobQuery.findObjects(new FindListener<PetPhoto>() {
            @Override
            public void done(List<PetPhoto> object, BmobException e) {
                if (e == null) {
                    PetPhotoAdapter adapter = new PetPhotoAdapter(PhotoListActivity.this, object);
                    mRecyclerView.setAdapter(adapter);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    ll_load_state.setVisibility(View.GONE);
                } else {

                }
            }
        });
    }

    private void initData2() {
        //只返回Person表的objectId这列的值
        BmobQuery<ChildPhoto> bmobQuery = new BmobQuery<ChildPhoto>();
        bmobQuery.addQueryKeys("imagePath,imageTitle");
        bmobQuery.findObjects(new FindListener<ChildPhoto>() {
            @Override
            public void done(List<ChildPhoto> object, BmobException e) {
                if (e == null) {
                    ChildPhotoAdapter adapter = new ChildPhotoAdapter(PhotoListActivity.this, object);
                    mRecyclerView.setAdapter(adapter);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    ll_load_state.setVisibility(View.GONE);
                } else {

                }
            }
        });
    }

    private void initData3() {
        //只返回Person表的objectId这列的值
        BmobQuery<PeoplePhoto> bmobQuery = new BmobQuery<PeoplePhoto>();
        bmobQuery.addQueryKeys("imagePath,imageTitle");
        bmobQuery.findObjects(new FindListener<PeoplePhoto>() {
            @Override
            public void done(List<PeoplePhoto> object, BmobException e) {
                if (e == null) {
                    PeoplePhotoAdapter adapter = new PeoplePhotoAdapter(PhotoListActivity.this, object);
                    mRecyclerView.setAdapter(adapter);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    ll_load_state.setVisibility(View.GONE);
                } else {

                }
            }
        });
    }

    private void initData4() {
        //只返回Person表的objectId这列的值
        BmobQuery<PhonePhoto> bmobQuery = new BmobQuery<PhonePhoto>();
        bmobQuery.addQueryKeys("imagePath,imageTitle");
        bmobQuery.findObjects(new FindListener<PhonePhoto>() {
            @Override
            public void done(List<PhonePhoto> object, BmobException e) {
                if (e == null) {
                    PhonePhotoAdapter adapter = new PhonePhotoAdapter(PhotoListActivity.this, object);
                    mRecyclerView.setAdapter(adapter);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    ll_load_state.setVisibility(View.GONE);
                } else {

                }
            }
        });
    }

    private void initData5() {
        //只返回Person表的objectId这列的值
        BmobQuery<OtherPhoto> bmobQuery = new BmobQuery<OtherPhoto>();
        bmobQuery.addQueryKeys("imagePath,imageTitle");
        bmobQuery.findObjects(new FindListener<OtherPhoto>() {
            @Override
            public void done(List<OtherPhoto> object, BmobException e) {
                if (e == null) {
                    OtherPhotoAdapter adapter = new OtherPhotoAdapter(PhotoListActivity.this, object);
                    mRecyclerView.setAdapter(adapter);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    ll_load_state.setVisibility(View.GONE);
                } else {

                }
            }
        });
    }
}
