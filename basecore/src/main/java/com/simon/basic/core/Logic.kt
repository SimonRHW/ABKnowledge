package com.simon.basic.core

import android.content.res.Configuration

/**
 * @author Simon
 * @date 2020/8/28
 * @desc
 */
interface Logic {

    var mApplication: LogicApplication

    fun onCreate()

    fun onTerminate()

    fun onLowMemory()

    fun onTrimMemory(level: Int)

    fun onConfigurationChanged(newConfig: Configuration?)
}