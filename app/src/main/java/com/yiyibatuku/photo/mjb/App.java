package com.yiyibatuku.photo.mjb;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.mob.MobSDK;

import java.util.LinkedList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.jpush.android.api.JPushInterface;


/**
 * Created by FLF on 2018/5/16.
 */

public class App extends Application {
    public List<Activity> mList = new LinkedList<Activity>();
    public static App instance;
    private static Context mAppContext;
    public App() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        Bmob.initialize(this,"c140eee577aedd9dbe4011c1b016e425");
        MobSDK.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
    public synchronized static App getInstance() {
        if (null == instance) {
            instance = new App();
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
            mList = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }


}
