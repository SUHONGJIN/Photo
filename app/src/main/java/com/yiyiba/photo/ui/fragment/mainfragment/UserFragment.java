package com.yiyiba.photo.ui.fragment.mainfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.longsh.optionframelibrary.OptionBottomDialog;
import com.longsh.optionframelibrary.OptionMaterialDialog;
import com.yiyiba.photo.R;
import com.yiyiba.photo.bean.User;
import com.yiyiba.photo.ui.activity.AboutActivity;
import com.yiyiba.photo.ui.activity.FeedBackActivity;
import com.yiyiba.photo.ui.activity.LikeActivity;
import com.yiyiba.photo.ui.activity.LoginActivity;
import com.yiyiba.photo.ui.activity.MainActivity;
import com.yiyiba.photo.ui.activity.ModifyUserDataActivity;
import com.yiyiba.photo.ui.activity.MyDownloadActivity;
import com.yiyiba.photo.ui.activity.SettingActivity;
import com.yiyiba.photo.utlis.ActivityCollector;
import com.yiyiba.photo.view.ItemView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

/**
 * Created by SuHongJin on 2018/12/9.
 */

public class UserFragment extends Fragment implements View.OnClickListener {
    private static final int TYPE_VALUE_NICK = 1;
    private static final int REQUEST_CODE_UPDATE_NICK = 2;
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

//        String url = "http://p0.so.qhmsg.com/bdr/1080__/t019706030483cc8cbf.jpg";
//        Glide.with(getContext())
//                .load(url)
//                .bitmapTransform(new BlurTransformation(getContext(), 8, 5))
//                .error(R.drawable.bg_image)
//                .into(mImage);
//        Glide.with(getContext())
//                .load(url)
//                .error(R.mipmap.icon_user_head)
//                .into(civ_head);

        if (BmobUser.isLogin()) {
            User user = BmobUser.getCurrentUser(User.class);
            if (user.getNick()!=null){
                tv_user_name.setText(user.getNick());
                my_item6.setVisibility(View.VISIBLE);
            }
        } else {
            Toasty.info(getContext(), "尚未登录!", Toast.LENGTH_SHORT, true).show();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.civ_user_head:
                if (!BmobUser.isLogin()){
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }else {
                    List<String> stringList = new ArrayList<String>();
                    stringList.add("拍照");
                    stringList.add("从相册选择");
                    final OptionBottomDialog optionBottomDialog = new OptionBottomDialog(getContext(), stringList);
                    optionBottomDialog.setItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            switch (position) {
                                case 0:
                                    Toasty.info(getContext(), "拍照", Toast.LENGTH_SHORT, true).show();
                                    break;
                                case 1:
                                    Toasty.info(getContext(), "从相册选择", Toast.LENGTH_SHORT, true).show();
                                    break;
                                default:
                                    break;
                            }
                            optionBottomDialog.dismiss();
                        }
                    });

                }
                break;
            case R.id.tv_user_name:
                if (!BmobUser.isLogin()){
                    startActivity(new Intent(getContext(), LoginActivity.class));
                } else {
                    Intent intent = new Intent(getContext(), ModifyUserDataActivity.class);
                    intent.putExtra("title", "修改昵称");
                    intent.putExtra("type", TYPE_VALUE_NICK);
                    startActivityForResult(intent,REQUEST_CODE_UPDATE_NICK);
                }
                break;
            case R.id.my_item1:
                startActivity(new Intent(getContext(), MyDownloadActivity.class));
                break;
            case R.id.my_item2:
                startActivity(new Intent(getContext(), LikeActivity.class));
                break;
            case R.id.my_item3:
                Intent intent = new Intent(new Intent(getContext(), SettingActivity.class));
                startActivityForResult(intent, REQUEST_CODE_UPDATE_NICK);
                break;
            case R.id.my_item4:
                startActivity(new Intent(getContext(), FeedBackActivity.class));
                break;
            case R.id.my_item5:
                startActivity(new Intent(getContext(), AboutActivity.class));
                break;
            case R.id.my_item6:

                final OptionMaterialDialog mMaterialDialog = new OptionMaterialDialog(getContext());
                mMaterialDialog.setTitle("小贴士：")
                        .setMessage("要退出登录吗？")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //退出登录，同时清除缓存用户对象。
                                BmobUser.logOut();
                                //退出APP
                                ActivityCollector.removeAllActivity();
                                //跳转到主页面
                                startActivity(new Intent(getContext(), MainActivity.class));
                                Toasty.success(getContext(), "已退出登录", Toast.LENGTH_SHORT, true).show();
                                mMaterialDialog.dismiss();
                            }
                        })
                        .setNegativeButton("取消",
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mMaterialDialog.dismiss();
                                    }
                                })
                        .setCanceledOnTouchOutside(true)
                        .show();

                break;

                default:break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_UPDATE_NICK) {
            //刷新用户昵称
            User user = BmobUser.getCurrentUser(User.class);
            if (user.getNick() != null) {
                tv_user_name.setText(user.getNick());
            }
        }
    }
}
