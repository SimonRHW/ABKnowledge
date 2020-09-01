package com.simon.log

import android.content.Context
import com.simon.log.console.ConsoleLog
import com.simon.log.local.LocalStorageLog
import com.simon.log.report.ReportLog

/**
 * @author Simon
 * @date 2020/5/1
 * @desc log配置管理
 */
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

    // TODO: 2020/7/2  这个开关的设置最好具有一定的灵活性，比如可以再加一层 System Property 的设置，
    //  使用 System Property 的好处是一旦设置之后，即使重启App System Property 中的变量依旧是设置之后的值，
    //  与 Android 中的 SharedPreference 非常相似
    private var isShowLog: Boolean = false
    private var logServerHost = ""
    private var namePrefix = "Slog"
    private var cacheDays = 7
    private var logLevel = 2
    private var publicKey = ""
    private var globalTag = ""

    /**
     * 设置log输出策略,本地存储策略需要设置logPath
     */
    fun setLogStrategy(strategy: LogStrategy): LoggerManager {
        logStrategy = strategy
        return this
    }

    /**
     * 是否输出日志
     */
    fun showLog(isShowLog: Boolean): LoggerManager {
        this.isShowLog = isShowLog
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
                ConsoleLog.getInstance(
                    isShowLog = isShowLog,
                    globalTag = globalTag
                )
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
         * 直接输出到终端，主要用于本地开发测试使用
         */
        CONSOLE,

        /**
         * 将日志进行本地存储，可按需上传到server端
         */
        LOCAL_STORAGE,

        /**
         * 实时发送到server端，例如车机在整车测试中不易连线debug，需要实时看数据交互
         */
        UPLOAD
    }
}