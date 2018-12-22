package com.yiyibaphoto.photo.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by SuHongJin on 2018/12/10.
 */

public class Photo1 extends BmobObject {
    private String imageUrl;
    private String imageTitle;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }


}
