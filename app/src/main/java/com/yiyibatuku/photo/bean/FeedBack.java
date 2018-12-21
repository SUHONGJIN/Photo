package com.yiyibatuku.photo.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by SuHongJin on 2018/12/10.
 */

public class FeedBack extends BmobObject {
    private String username;
    private String nick;
    private String content;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
