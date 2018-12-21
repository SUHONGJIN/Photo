package com.yiyibatuku.photo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiyibatuku.photo.R;

/**
 * Created by SuHongJin on 2018/12/9.
 */

public class ClassifyItemView extends LinearLayout {


    private ImageView iv_classify;
    private TextView tv_title__classify;
    private TextView tv_content__classify;
    private LinearLayout ll_itemview_classify;

    public ClassifyItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.view_item_classify_layout, this);
        iv_classify = (ImageView) view.findViewById(R.id.iv_classify);
        tv_title__classify = (TextView) view.findViewById(R.id.tv_title__classify);
        tv_content__classify = (TextView) view.findViewById(R.id.tv_content__classify);
        ll_itemview_classify = (LinearLayout) view.findViewById(R.id.ll_itemview_classify);

        //获取属性的值
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClassifyItemView);

        int setClassifyIcon = typedArray.getResourceId(R.styleable.ClassifyItemView_setClassifyIcon, R.drawable.bg_image);
        String setClassifyTitle = typedArray.getString(R.styleable.ClassifyItemView_setClassifyTitle);
        String setClassifyContent = typedArray.getString(R.styleable.ClassifyItemView_setClassifyContent);

        setView(setClassifyIcon, setClassifyTitle, setClassifyContent);

        typedArray.recycle();
    }

    /**
     * 设置参数
     *
     * @param setClassifyIcon
     * @param setClassifyTitle
     * @param setClassifyContent
     */
    private void setView(int setClassifyIcon, String setClassifyTitle, String setClassifyContent) {
        if (setClassifyIcon != 0) {
            iv_classify.setImageResource(setClassifyIcon);
        }
        if (setClassifyTitle != null) {
            tv_title__classify.setText(setClassifyTitle);
        }
        if (setClassifyContent != null) {
            tv_content__classify.setText(setClassifyContent);
        }
    }

    /**
     * 设置点击事件
     *
     * @param listener
     */
    public void setOnItemViewClickListener(OnClickListener listener) {
        ll_itemview_classify.setOnClickListener(listener);
    }

}
