package com.yiyibaphoto.photo.utlis;

import android.content.Context;
import android.content.SharedPreferences.Editor;


/**
 * Created by SuHongJin on 2018/12/12.
 */

public class SharedPreferencesUtils {
    private static final String FILE_NAME = "photo";
    private static final String VALUE_NAME = "guide";
    private static final String VALUE_NAME_OPEN_NOTICE = "notice";

    //写入
    public static void putWelcomeGuideBoolean(Context context,Boolean isFirst){
        Editor editor =context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).edit();
        editor.putBoolean(VALUE_NAME,isFirst);
        editor.commit();
    }
    //获取
    public static boolean getWelcomeGuideBoolean(Context context){
        return context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).getBoolean(VALUE_NAME,false);
    }
    //写入保存设置里的信息
    public static void putisOpenNotice(Context context,Boolean isOpen){
        Editor editor =context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).edit();
        editor.putBoolean(VALUE_NAME_OPEN_NOTICE,isOpen);
        editor.commit();
    }
    //获取设置里的配置信息
    public  static  boolean getisOpenNotice(Context context){
        return context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).getBoolean(VALUE_NAME_OPEN_NOTICE,true);  //默认打开
    }

}
