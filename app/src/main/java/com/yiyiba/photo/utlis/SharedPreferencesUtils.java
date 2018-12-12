package com.yiyiba.photo.utlis;

import android.content.Context;
import android.content.SharedPreferences.Editor;


/**
 * Created by SuHongJin on 2018/12/12.
 */

public class SharedPreferencesUtils {
    private static final String FILE_NAME = "photo";
    private static final String VALUE_NAME = "guide";
    //获取
    public static boolean getWelcomeGuideBoolean(Context context){
        return context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).getBoolean(VALUE_NAME,false);
    }
    //写入
    public static void putWelcomeGuideBoolean(Context context,Boolean isFirst){
        Editor editor =context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).edit();
        editor.putBoolean(VALUE_NAME,isFirst);
        editor.commit();
    }

}
