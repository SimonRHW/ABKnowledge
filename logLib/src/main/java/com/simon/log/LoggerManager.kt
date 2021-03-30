package com.simon.log

import android.annotation.SuppressLint
import android.content.Context
import com.simon.log.console.ConsoleLog
import com.simon.log.local.LocalStorageLog
import com.simon.log.report.ReportLog
import com.tencent.mars.xlog.Xlog
import java.io.File

/**
 * @author Simon
 * @date 2020/5/1
 * @desc log配置管理
 */
class LoggerManager private constructor(
    private val context: Context,
    private val isShowLog: Boolean
) {
    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: LoggerManager? = null

        fun getInstance(context: Context, isShowLog: Boolean = true) =
            instance ?: synchronized(this) {
                instance ?: LoggerManager(context, isShowLog).also {
                    Logger.setLogManager(it)
                    it.initLocalLogParam()
                    instance = it
                }
            }
    }

    private var logStrategy = LogStrategy.CONSOLE
    private var logLevel = 1
    private var globalTag = ""
    private var logFileDir = ""
    private var cacheDir = ""
    private var namePrefix = "Slog"
    private var cacheDays = 7
    private var logServerHost = ""

    @Volatile
    private var localStorageLogInit = false

    /**
     * 设置log输出策略,本地存储策略需要设置logPath
     */
    fun setLogStrategy(strategy: LogStrategy): LoggerManager {
        logStrategy = strategy
        return this
    }

    /**
     * 设置输出日志级别
     */
    fun setLogLevel(logLevel: Int): LoggerManager {
        this.logLevel = logLevel
        return this
    }

    /**
     * 只对LogStrategy.CONSOLE 生效
     */
    fun setGlobalTag(globalTag: String): LoggerManager {
        this.globalTag = globalTag
        return this
    }

    /**
     * 设置上报日志的Server Host
     */
    fun setLogServerHost(logServerHost: String): LoggerManager {
        this.logServerHost = logServerHost
        return this
    }

    internal fun provideLogImpl(): ILog {
        return when (logStrategy) {
            LogStrategy.CONSOLE -> {
                ConsoleLog.getInstance(
                    isShowLog = isShowLog,
                    globalTag = globalTag,
                    logLevel = logLevel
                )
            }
            LogStrategy.LOCAL_STORAGE -> {
                LocalStorageLog.getInstance(
                    isShowLog = isShowLog,
                    globalTag = globalTag,
                    logLevel = logLevel
                )
            }
            LogStrategy.UPLOAD -> {
                ReportLog.getInstance(
                    isShowLog = isShowLog,
                    host = logServerHost,
                    logLevel = logLevel
                )
            }
        }
    }

    enum class LogStrategy {
        /**
         * 直接输出到终端，主要用于本地开发测试使用
         */
        CONSOLE,

        /**
         * 将日志进行本地存储，可按需上传到server端
         */
        LOCAL_STORAGE,

        /**
         * 实时发送到server端，例如车机在整车测试中不能Adb debug，需要实时看数据交互
         */
        UPLOAD
    }

    private fun initLocalLogParam() {
        if (localStorageLogInit) {
            return
        }
        if (logFileDir.isEmpty()) {
            logFileDir = context.filesDir.absolutePath + File.separator + "log"
        }
        if (cacheDir.isEmpty()) {
            cacheDir = context.filesDir.absolutePath + File.separator + "cacheLog"
        }
        System.loadLibrary("c++_shared")
        System.loadLibrary("marsxlog")
        com.tencent.mars.xlog.Log.setLogImp(Xlog())
        if (isShowLog) {
            com.tencent.mars.xlog.Log.setConsoleLogOpen(true)
            com.tencent.mars.xlog.Log.appenderOpen(
                BaseLog.LEVEL_ALL,
                Xlog.AppednerModeAsync,
                cacheDir,
                logFileDir,
                namePrefix,
                cacheDays
            )
        } else {
            com.tencent.mars.xlog.Log.setConsoleLogOpen(false)
            com.tencent.mars.xlog.Log.appenderOpen(
                BaseLog.LEVEL_INFO,
                Xlog.AppednerModeAsync,
                cacheDir,
                logFileDir,
                namePrefix,
                cacheDays
            )
        }
        localStorageLogInit = true
    }

}