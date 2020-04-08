package com.simon.basic.log

import android.content.Context
import com.tencent.mars.xlog.Xlog
import timber.log.Timber

class LogManager(
    context: Context,
    isShowLog: Boolean,
    cachePath: String,
    logPath: String,
    namePrefix: String = "Slog",
    cacheDays: Int = 7,
    publicKey: String = ""
) {
    init {
        System.loadLibrary("c++_shared")
        System.loadLibrary("marsxlog")
        Xlog.appenderOpen(
            Xlog.LEVEL_ALL,
            Xlog.AppednerModeAsync,
            cachePath,
            logPath,
            namePrefix,
            cacheDays,
            publicKey
        )
        if (isShowLog) {
            Xlog.setConsoleLogOpen(true)
            Xlog.setLogLevel(Xlog.LEVEL_ALL)
            Timber.plant(Timber.DebugTree())
        } else {
            Xlog.setConsoleLogOpen(false)
            Xlog.setLogLevel(Xlog.LEVEL_INFO)
            Timber.plant(CrashReportingTree())
        }
    }
}