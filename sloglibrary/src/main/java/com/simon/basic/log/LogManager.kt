package com.simon.basic.log

import android.content.Context
import android.os.Environment
import com.tencent.mars.xlog.Xlog
import timber.log.Timber
import java.io.File

class LogManager() {

    companion object {
        private lateinit var CACHE_PATH: String;
        private lateinit var LOG_PATH: String;
        private lateinit var XLOG_PUBLIC_KEY: String;
        private var CACHE_DAYS: Int = 7;
        private lateinit var Name_Prefix: String;
    }

    open fun initLog(
        context: Context,
        isDebug: Boolean
    ) {
        initLog(context, isDebug, "", "", 7, "")
    }

    open fun initLog(
        context: Context,
        isDebug: Boolean,
        cachePath: String,
        logPath: String,
        cacheDays: Int,
        publicKey: String
    ) {
        CACHE_PATH = if (cachePath.isNullOrEmpty()) {
            context.filesDir.toString() + "/xlog"
        } else {
            cachePath;
        }
        LOG_PATH = if (logPath.isNullOrEmpty()) {
            Environment.getExternalStorageDirectory().toString() + File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator + "SLOG";
        } else {
            logPath
        }
        XLOG_PUBLIC_KEY = if (publicKey.isNullOrEmpty()) {
            ""
        } else {
            publicKey
        }
        CACHE_DAYS = if (cacheDays > 0) {
            cacheDays
        } else {
            7
        }
        initXLog(isDebug)
        if (isDebug) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }


    /**
     * XLog
     */
    private fun initXLog(isShowLog: Boolean) {
        System.loadLibrary("stlport_shared")
        System.loadLibrary("marsxlog")
        if (isShowLog) {
            Xlog.appenderOpen(
                Xlog.LEVEL_ALL,
                Xlog.AppednerModeAsync,
                CACHE_PATH,
                LOG_PATH,
                Name_Prefix,
                CACHE_DAYS,
                XLOG_PUBLIC_KEY
            )
            Xlog.setConsoleLogOpen(true)
            Xlog.setLogLevel(Xlog.LEVEL_ALL)
        } else {
            Xlog.appenderOpen(
                Xlog.LEVEL_INFO,
                Xlog.AppednerModeAsync,
                CACHE_PATH,
                LOG_PATH,
                Name_Prefix,
                CACHE_DAYS,
                XLOG_PUBLIC_KEY
            )
            Xlog.setConsoleLogOpen(false)
            Xlog.setLogLevel(Xlog.LEVEL_INFO)
        }
    }

}