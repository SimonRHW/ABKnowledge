package com.simon.log

import android.annotation.SuppressLint
import com.simon.log.console.ConsoleLog

/**
 * @author Simon
 * @date 2020/5/1
 * @desc log配置管理
 */
@SuppressLint("StaticFieldLeak")
object LoggerManager {

    var enableLog: Boolean = true
    private var mLogProvider: LogProvider = DebugLogProvider()
    var logLevel = BaseLog.LEVEL_INFO

    /**
     * 是否开启日志输出
     */
    fun consoleLog(enable: Boolean): LoggerManager {
        this.enableLog = enable
        return this
    }

    /**
     * 设置日志收集级别
     */
    fun setLogLevel(logLevel: Int): LoggerManager {
        this.logLevel = logLevel
        return this
    }

    /**
     * 设置不同的Log输出模式
     * @see com.simon.log.console.ConsoleLogProvider
     * @see com.simon.log.local.LocalStorageLogProvider
     * @see com.simon.log.report.ReportLogProvider
     */
    fun setLogProvider(logProvider: LogProvider) {
        this.mLogProvider = logProvider
    }

    internal fun logProvider(): LogProvider {
        return mLogProvider
    }

    /**
     * 直接输出到终端
     */
    internal class DebugLogProvider : LogProvider {
        override fun provideLog(): ILog {
            return ConsoleLog.getInstance(
                consoleLog = true,
                globalTag = "SLog",
                logLevel = 0
            )
        }
    }

}