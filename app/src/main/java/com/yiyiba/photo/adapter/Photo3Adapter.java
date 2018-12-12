package com.yiyiba.photo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yiyiba.photo.R;
import com.yiyiba.photo.bean.Photo3;
import com.yiyiba.photo.ui.activity.ShowImageActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by SuHongJin on 2018/12/10.
 */

public class Photo3Adapter extends RecyclerView.Adapter<Photo3Adapter.ViewHolder>{
    private List<Photo3> photo3List=new ArrayList<>();
    private Context context;

    public Photo3Adapter(List<Photo3> photo3, Context context){
        this.photo3List = photo3;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_photo_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final int position=viewHolder.getAdapterPosition();

        viewHolder.tv_photo.setText(photo3List.get(i).getImageTitle3());
        Glide.with(context).load(photo3List.get(i).getImageUrl3()).placeholder(R.drawable.bg_default_image).into(viewHolder.iv_photo);
        //点击事件
        viewHolder.ll_photo_itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowImageActivity.class);
                intent.putExtra("image_url",photo3List.get(position).getImageUrl3());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photo3List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_photo;
        private TextView tv_photo;
        private LinearLayout ll_photo_itemview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_photo = (ImageView)itemView.findViewById(R.id.iv_photo);
            tv_photo = (TextView) itemView.findViewById(R.id.tv_photo);
            ll_photo_itemview=(LinearLayout)itemView.findViewById(R.id.ll_photo_itemview);
        }
    }

}
