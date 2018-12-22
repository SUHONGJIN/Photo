package com.yiyibaphoto.photo.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by SuHongJin on 2018/12/17.
 */

public class PetPhoto extends BmobObject {
    private String imageTitle;
    private String imagePath;

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
