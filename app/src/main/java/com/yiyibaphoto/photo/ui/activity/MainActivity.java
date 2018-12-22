package com.yiyibaphoto.photo.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yiyibaphoto.photo.R;
import com.yiyibaphoto.photo.common.BaseActivity;
import com.yiyibaphoto.photo.ui.fragment.mainfragment.ClassifyFragment;
import com.yiyibaphoto.photo.ui.fragment.mainfragment.PhotoFragment;
import com.yiyibaphoto.photo.ui.fragment.mainfragment.UserFragment;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private PhotoFragment fg_photo;
    private ClassifyFragment fg_classify;
    private UserFragment fg_user;
    private FrameLayout fl_content;
    private RadioButton rb_photo;
    private RadioButton rb_classify;
    private RadioButton rb_user;
    private RadioGroup rg_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        rb_photo = (RadioButton) findViewById(R.id.rb_photo);
        rb_classify = (RadioButton) findViewById(R.id.rb_classify);
        rb_user = (RadioButton) findViewById(R.id.rb_user);
        rg_navigation = (RadioGroup) findViewById(R.id.rg_navigation);
        rg_navigation.setOnCheckedChangeListener(this);
        rb_photo.setChecked(true);
    }

    /**
     * 点击切换fragment
     * @param radioGroup
     * @param i
     */
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (i) {
            case R.id.rb_photo:
                if (fg_photo!=null){
                    fragmentTransaction.show(fg_photo);
                }else {
                    fg_photo = new PhotoFragment();
                    fragmentTransaction.add(R.id.fl_content,fg_photo);
                }
                break;
            case R.id.rb_classify:
                if (fg_classify!=null){
                    fragmentTransaction.show(fg_classify);
                }else {
                    fg_classify = new ClassifyFragment();
                    fragmentTransaction.add(R.id.fl_content,fg_classify);
                }
                break;
            case R.id.rb_user:
                if (fg_user!=null){
                    fragmentTransaction.show(fg_user);
                }else {
                    fg_user = new UserFragment();
                    fragmentTransaction.add(R.id.fl_content,fg_user);
                }
                break;
                default:break;
        }
        fragmentTransaction.commit();
    }

    /**
     * 隐藏所有fragment
     * @param fragmentTransaction
     */
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if (fragmentTransaction == null){
            return;
        }
        if (fg_photo!=null){
            fragmentTransaction.hide(fg_photo);
        }
        if (fg_classify!=null){
            fragmentTransaction.hide(fg_classify);
        }
        if (fg_user!=null){
            fragmentTransaction.hide(fg_user);
        }

    }


}
