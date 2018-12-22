package com.yybtuku.photo.utlis;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**活动管理类
 * Created by SuHongJin on 2018/12/10.
 */

public class ActivityCollector {

    private static List<Activity> activityList=new ArrayList<>();

    /**
     * 添加activity
     * @param activity
     */
    public static void addActivity(Activity activity){
        activityList.add(activity);
    }

    /**
     * 移除activity
     * @param activity
     */
    public static void removeActivity(Activity activity){
        activityList.remove(activity);
    }

    /**
     * 移除所有activity
     */
    public static void removeAllActivity(){
        for (Activity activity : activityList){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
        activityList.clear();
    }

}
