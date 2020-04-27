package com.simon.log

import android.content.Context
import com.simon.log.console.ConsoleLog
import com.simon.log.local.LocalStorageLog
import com.simon.log.report.ReportLog

class LoggerManager private constructor(
    private var context: Context,
    private var logPath: String
) {
    companion object {
        @Volatile
        private var instance: LoggerManager? = null

        fun getInstance(context: Context, logPath: String = "") =
            instance ?: synchronized(this) {
                instance ?: LoggerManager(context.applicationContext, logPath).also {
                    Logger.setLogManager(it)
                    instance = it
                }
            }
    }

    private var logStrategy = LogStrategy.CONSOLE
    private var isShowLog: Boolean = false
    private var logServerHost = ""
    private var namePrefix = "Slog"
    private var cacheDays = 7
    private var logLevel = 2
    private var publicKey = ""

    /**
     * 设置log输出策略,本地存储策略需要设置logPath
     */
    fun setLogStrategy(strategy: LogStrategy): LoggerManager {
        logStrategy = strategy
        return this
    }

    fun showLog(isShowLog: Boolean): LoggerManager {
        this.isShowLog = isShowLog
        return this
    }


    fun setLogLevel(logLevel: Int): LoggerManager {
        this.logLevel = logLevel
        return this
    }

    /**
     * 设置本地存储日志路径
     */
    fun setLogPath(logPath: String): LoggerManager {
        this.logPath = logPath
        return this
    }

    /**
     * 设置本地存储文件前缀名
     */
    fun setLogNamePrefix(namePrefix: String): LoggerManager {
        this.namePrefix = namePrefix
        return this
    }

    /**
     * 设置本地存储文件保存天数
     */
    fun setLogCacheDays(cacheDays: Int): LoggerManager {
        this.cacheDays = cacheDays
        return this
    }


    /**
     * 设置文件加密公钥
     */
    fun setLogPublicKey(publicKey: String): LoggerManager {
        this.publicKey = publicKey
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
                ConsoleLog.getInstance(isShowLog = isShowLog)
            }
            LogStrategy.LOCAL_STORAGE -> {
                LocalStorageLog.getInstance(
                    context = context,
                    isShowLog = isShowLog,
                    logPath = logPath,
                    namePrefix = namePrefix,
                    cacheDays = cacheDays,
                    logLevel = logLevel,
                    publicKey = publicKey
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
         * 直接输出到终端
         */
        CONSOLE,

        /**
         * 进行本地存储 按需上传server端
         */
        LOCAL_STORAGE,

        /**
         * 直接输出到server端
         */
        UPLOAD
    }
}