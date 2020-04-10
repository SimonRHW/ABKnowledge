package com.simon.basic.knowledge.global

import android.app.Application
import android.os.Environment
import com.simon.basic.log.LogManager
import java.io.File

class ABKApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LogManager(
            context = this,
            isShowLog = true,
            cachePath = this.filesDir.toString() + "/xlog",
            logPath = Environment.getExternalStorageDirectory().toString() + File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator + "SLOG"
        )
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}