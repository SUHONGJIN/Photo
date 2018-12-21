package com.yiyibatuku.photo.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by SuHongJin on 2018/12/9.
 */

public class User extends BmobUser {
    private String nick;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
