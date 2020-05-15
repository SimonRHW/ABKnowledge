package com.simon.basic.core;

import android.content.res.Configuration;

import androidx.annotation.NonNull;

/**
 * @author Simon
 * @date 2020/5/10
 * @desc 需要子类去实现一些方法
 */
public interface ILogic {

    void onCreate();

    void onTerminate();

    void onLowMemory();

    void onTrimMemory(int level);

    void onConfigurationChanged(Configuration newConfig);
}
