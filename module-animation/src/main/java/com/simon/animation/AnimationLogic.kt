package com.simon.animation

import android.content.res.Configuration
import com.simon.basic.core.Logic
import com.simon.basic.core.LogicApplication
import com.simon.log.Logger

/**
 * @author Simon
 * @date 2020/11/3
 * @desc
 */
class AnimationLogic(override var mApplication: LogicApplication) : Logic {

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