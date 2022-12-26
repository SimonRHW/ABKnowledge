package com.simon.log.local

import android.content.Context
import com.simon.log.BaseLog
import com.simon.log.ILog
import com.simon.log.LogProvider
import com.simon.log.LoggerManager
import com.tencent.mars.xlog.Log
import com.tencent.mars.xlog.Xlog
import java.io.File

class LocalStorageLogProvider(
    private val context: Context,
    private val logLevel: Int = BaseLog.LEVEL_INFO,
    private val logFileDir: String = context.filesDir.absolutePath + File.separator + "log",
    private val cacheDir: String = context.cacheDir.absolutePath + File.separator + "log",
    private val namePrefix: String = "Slog",
    private val cacheDays: Int = 7,
) : LogProvider {

    init {
        System.loadLibrary("c++_shared")
        System.loadLibrary("marsxlog")
        Log.setLogImp(Xlog())
        if (LoggerManager.enableLog) {
            Log.setConsoleLogOpen(true)
            Log.appenderOpen(
                logLevel,
                Xlog.AppednerModeAsync,
                cacheDir,
                logFileDir,
                namePrefix,
                cacheDays
            )
        } else {
            Log.setConsoleLogOpen(false)
            Log.appenderOpen(
                logLevel,
                Xlog.AppednerModeAsync,
                cacheDir,
                logFileDir,
                namePrefix,
                cacheDays
            )
        }
    }

    override fun provideLog(): ILog {
        return LocalStorageLog.getInstance(
            consoleLog = LoggerManager.enableLog,
            logLevel = LoggerManager.logLevel
        )
    }

}