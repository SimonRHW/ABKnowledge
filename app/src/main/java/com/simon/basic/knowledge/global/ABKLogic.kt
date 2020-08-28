package com.simon.basic.knowledge.global

import android.content.res.Configuration
import com.simon.basic.core.Logic
import com.simon.basic.core.LogicApplication
import com.simon.log.Logger

/**
 * @author Simon
 * @date 2020/8/28
 * @desc
 */
class ABKLogic(override var mApplication: LogicApplication) : Logic {

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