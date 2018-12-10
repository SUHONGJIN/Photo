package com.yiyiba.photo.ui.fragment.mainfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yiyiba.photo.R;
import com.yiyiba.photo.ui.activity.LoginActivity;
import com.yiyiba.photo.view.ItemView;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by SuHongJin on 2018/12/9.
 */

public class UserFragment extends Fragment implements View.OnClickListener {
    private ImageView mImage;
    private CircleImageView civ_head;
    private CircleImageView civ_user_head;
    private TextView tv_user_name;
    private ItemView my_item1;
    private ItemView my_item2;
    private ItemView my_item3;
    private ItemView my_item4;
    private ItemView my_item5;
    private ItemView my_item6;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mImage = (ImageView) view.findViewById(R.id.mImage);
        civ_head = (CircleImageView) view.findViewById(R.id.civ_user_head);
        civ_user_head = (CircleImageView) view.findViewById(R.id.civ_user_head);
        civ_user_head.setOnClickListener(this);
        tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
        tv_user_name.setOnClickListener(this);
        my_item1 = (ItemView) view.findViewById(R.id.my_item1);
        my_item1.setOnClickListener(this);
        my_item2 = (ItemView) view.findViewById(R.id.my_item2);
        my_item2.setOnClickListener(this);
        my_item3 = (ItemView) view.findViewById(R.id.my_item3);
        my_item3.setOnClickListener(this);
        my_item4 = (ItemView) view.findViewById(R.id.my_item4);
        my_item4.setOnClickListener(this);
        my_item5 = (ItemView) view.findViewById(R.id.my_item5);
        my_item5.setOnClickListener(this);
        my_item6 = (ItemView) view.findViewById(R.id.my_item6);
        my_item6.setOnClickListener(this);
    }

    /**
     * 初始化
     */
    private void initData() {

        Glide.with(getContext())
                .load("http://img1.imgtn.bdimg.com/it/u=789661362,2109010345&fm=200&gp=0.jpg")
                .bitmapTransform(new BlurTransformation(getContext(), 8, 5))
                .error(R.drawable.bg_image)
                .into(mImage);
        Glide.with(getContext())
                .load("http://img1.imgtn.bdimg.com/it/u=789661362,2109010345&fm=200&gp=0.jpg")
                .error(R.drawable.bg_image)
                .into(civ_head);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.civ_user_head:
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
            case R.id.tv_user_name:
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
            case R.id.my_item1:
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
            case R.id.my_item2:
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
            case R.id.my_item3:
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
            case R.id.my_item4:
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
            case R.id.my_item5:
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
            case R.id.my_item6:
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;

        }
    }
}
