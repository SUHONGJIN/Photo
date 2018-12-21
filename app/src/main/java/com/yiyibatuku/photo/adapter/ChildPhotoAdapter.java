package com.yiyibatuku.photo.adapter;

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
import com.yiyibatuku.photo.R;
import com.yiyibatuku.photo.bean.ChildPhoto;
import com.yiyibatuku.photo.ui.activity.ShowImageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuHongJin on 2018/12/17.
 */

public class ChildPhotoAdapter extends RecyclerView.Adapter<ChildPhotoAdapter.ViewHolder> {
    private List<ChildPhoto> photoList = new ArrayList<>();
    private Context context;

    public ChildPhotoAdapter(Context context, List<ChildPhoto> childPhoto) {
        this.photoList = childPhoto;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_photolist_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        String imageTitle = photoList.get(i).getImageTitle();
        String imagePath = photoList.get(i).getImagePath();

        viewHolder.tv_photo_title.setText(imageTitle);
        Glide.with(context).load(imagePath).into(viewHolder.iv_photo_image);

        final int position = viewHolder.getAdapterPosition();
        //点击事件
        viewHolder.ll_itemview_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowImageActivity.class);
                intent.putExtra("image_url", photoList.get(position).getImagePath());
                intent.putExtra("image_title", photoList.get(position).getImageTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_photo_image;
        private TextView tv_photo_title;
        private LinearLayout ll_itemview_photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_photo_image = (ImageView) itemView.findViewById(R.id.iv_photo_image);
            tv_photo_title = (TextView) itemView.findViewById(R.id.tv_photo_title);
            ll_itemview_photo = (LinearLayout) itemView.findViewById(R.id.ll_itemview_photo);
        }
    }
}
