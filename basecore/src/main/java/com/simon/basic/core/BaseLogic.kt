package com.simon.basic.core

import android.content.res.Configuration
import androidx.annotation.NonNull
import com.simon.log.Logger

/**
 * @author Simon
 * @date 2020/5/10
 * @desc
 */
class BaseLogic : ILogic {

    private var mApplication: LogicApplication? = null

    fun setApplication(@NonNull application: LogicApplication) {
        this.mApplication = application
        Logger.debug("setApplication() called with :LogicApplication = $application")
    }

    override fun onCreate() {
        Logger.debug("onCreate() called")
    }

    override fun onTerminate() {
        Logger.debug("onTerminate() called")
    }

    override fun onLowMemory() {
        Logger.debug("onLowMemory() called")
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        Logger.debug("onConfigurationChanged() called with: newConfig = $newConfig")
    }

    override fun onTrimMemory(level: Int) {
        Logger.debug("onTrimMemory() called with: level = [$level]")
    }
}