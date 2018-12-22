package com.yybtuku.photo.common;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yybtuku.photo.R;

/**
 * Created by SuHongJin on 2018/12/11.
 */

public class ViewHolder extends RecyclerView.ViewHolder{

     public static ImageView iv_photo;
     public static TextView tv_photo;
     public static LinearLayout ll_photo_itemview;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        iv_photo = (ImageView)itemView.findViewById(R.id.iv_photo);
        tv_photo = (TextView) itemView.findViewById(R.id.tv_photo);
        ll_photo_itemview=(LinearLayout)itemView.findViewById(R.id.ll_photo_itemview);
    }
}