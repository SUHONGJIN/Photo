package com.yiyibaphoto.photo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiyibaphoto.photo.R;

/**
 * Created by SuHongJin on 2018/12/9.
 */

public class ItemView extends LinearLayout {
    private ImageView item_view_image;
    private TextView item_view_text;
    private LinearLayout ll_itemview;

    public ItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.view_itemview_layout, this);
        item_view_image = (ImageView) view.findViewById(R.id.item_view_image);
        item_view_text = (TextView) view.findViewById(R.id.item_view_text);
        ll_itemview = (LinearLayout) view.findViewById(R.id.ll_itemview);

        //获取属性的值
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemView);

        int setIcon = typedArray.getResourceId(R.styleable.ItemView_setIcon, R.drawable.bg_image);
        String setText = typedArray.getString(R.styleable.ItemView_setText);

        setView(setIcon, setText);

        typedArray.recycle();
    }

    /**
     * 设置参数
     *
     * @param setIcon
     * @param setText
     */
    private void setView(int setIcon, String setText) {
        if (setIcon != 0) {
            item_view_image.setImageResource(setIcon);
        }
        if (setText != null) {
            item_view_text.setText(setText);
        }
    }

    /**
     * 设置点击事件
     *
     * @param listener
     */
    public void setOnItemViewClickListener(OnClickListener listener) {
        ll_itemview.setOnClickListener(listener);
    }

}
