package com.yiyiba.photo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.yiyiba.photo.R;
import com.yiyiba.photo.bean.Photo1;
import com.yiyiba.photo.bean.Photo2;
import com.yiyiba.photo.common.ViewHolder;
import com.yiyiba.photo.ui.activity.ShowImageActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by SuHongJin on 2018/12/10.
 */

public class Photo2Adapter extends RecyclerView.Adapter<ViewHolder>{
    private List<Photo2> photo1List = new ArrayList<>();
    private Context context;

    public Photo2Adapter(List<Photo2> photo2, Context context){
        this.photo1List = photo2;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_photo_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        final int position=viewHolder.getAdapterPosition();

        viewHolder.tv_photo.setText(photo1List.get(i).getImageTitle2());
        Glide.with(context).load(photo1List.get(i).getImageUrl2()).into(viewHolder.iv_photo);
        //点击事件
        viewHolder.ll_photo_itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowImageActivity.class);
                intent.putExtra("image_url",photo1List.get(position).getImageUrl2());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photo1List.size();
    }

}
