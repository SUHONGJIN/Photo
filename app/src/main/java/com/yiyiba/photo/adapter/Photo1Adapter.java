package com.yiyiba.photo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yiyiba.photo.R;
import com.yiyiba.photo.bean.Photo1;

import java.util.List;

/**
 * Created by SuHongJin on 2018/12/10.
 */

public class Photo1Adapter extends RecyclerView.Adapter<Photo1Adapter.ViewHolder>{
    private List<Photo1> photo1List;
    private Context context;

    public Photo1Adapter(List<Photo1> photo1,Context context){
        this.photo1List = photo1;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_photo_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Photo1Adapter.ViewHolder viewHolder, int i) {
        Log.i("tag2","==========222=======");
        Log.i("tag2",photo1List.get(i).getImageTitle()+"=================");
        Log.i("tag2",photo1List.get(i).getImageUrl()+"=================");
        viewHolder.tv_photo.setText(photo1List.get(i).getImageTitle());

        Glide.with(context).load(photo1List.get(i).getImageUrl()).into(viewHolder.iv_photo);


    }

    @Override
    public int getItemCount() {
        return photo1List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_photo;
        private TextView tv_photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_photo = (ImageView)itemView.findViewById(R.id.iv_photo);
            tv_photo = (TextView) itemView.findViewById(R.id.tv_photo);
        }
    }
}
