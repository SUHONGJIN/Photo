package com.yiyiba.photo.utlis;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

/**
 * Created by SuHongJin on 2018/12/17.
 */

public class HttpUtil {
    public static void sendOkHttpRequest(String address , Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
