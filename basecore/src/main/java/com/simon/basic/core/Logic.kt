package com.simon.basic.core

import android.content.res.Configuration
import androidx.annotation.NonNull

/**
 * @author Simon
 * @date 2020/8/28
 * @desc
 */
interface Logic {

    var mApplication: LogicApplication

    fun setApplication(@NonNull application: LogicApplication) {
        this.mApplication = application
    }

    fun onCreate()

    fun onTerminate()

    fun onLowMemory()

    fun onTrimMemory(level: Int)

    fun onConfigurationChanged(newConfig: Configuration?)
}