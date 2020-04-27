package com.simon.basic.knowledge.global

import android.app.Application
import com.simon.basic.core.platform.AppStatusManager
import com.simon.log.Logger
import com.simon.log.LoggerManager

class ABKApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        LoggerManager.getInstance(this)
            .setLogStrategy(LoggerManager.LogStrategy.CONSOLE)
            .showLog(true)
            .setLogLevel(0)

        AppStatusManager.instance.init(this)

        Logger.info("onCreate")
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}