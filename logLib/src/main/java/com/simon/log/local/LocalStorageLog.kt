package com.simon.log.local

import android.content.Context
import com.simon.log.ILog
import com.tencent.mars.xlog.Xlog
import timber.log.Timber

/**
 * @author Simon
 * @date 2020/5/1
 * @desc
 */
class LocalStorageLog private constructor(
    context: Context,
    private var isShowLog: Boolean,
    private var logPath: String,
    namePrefix: String,
    cacheDays: Int,
    logLevel: Int,
    publicKey: String
) : ILog {

    companion object {
        @Volatile
        private var instance: LocalStorageLog? = null

        fun getInstance(
            context: Context,
            isShowLog: Boolean,
            logPath: String,
            namePrefix: String,
            cacheDays: Int,
            logLevel: Int,
            publicKey: String
        ) =
            instance ?: synchronized(this) {
                instance ?: LocalStorageLog(
                    context,
                    isShowLog,
                    logPath,
                    namePrefix,
                    cacheDays,
                    logLevel,
                    publicKey
                ).also {
                    instance = it
                    Timber.plant(LocalStorageTree())
                }
            }
    }

    init {
        System.loadLibrary("c++_shared")
        System.loadLibrary("marsxlog")
        Xlog.appenderOpen(
            Xlog.LEVEL_ALL,
            Xlog.AppednerModeAsync,
            context.applicationContext.filesDir.toString() + "/log",
            logPath,
            namePrefix,
            cacheDays,
            publicKey
        )
        Xlog.setConsoleLogOpen(isShowLog)
        Xlog.setLogLevel(logLevel)
    }

    private fun verifyLogPath(): Boolean {
        return this.logPath.isNotEmpty()
    }

    /**
     * 是否可以保存日志
     */
    private fun canSave(): Boolean {
        return verifyLogPath() && isShowLog
    }

    override fun verbose(message: String?, vararg args: Any?) {
        if (canSave()) {
            Timber.v(message, args)
        }
    }

    override fun verbose(t: Throwable?, message: String?, vararg args: Any?) {
        if (canSave()) {
            Timber.v(t, message, args)
        }
    }

    override fun verbose(t: Throwable?) {
        if (canSave()) {
            Timber.v(t)
        }
    }

    override fun debug(message: String?, vararg args: Any?) {
        if (canSave()) {
            Timber.d(message, args)
        }
    }

    override fun debug(t: Throwable?, message: String?, vararg args: Any?) {
        if (canSave()) {
            Timber.d(t, message, args)
        }
    }

    override fun debug(t: Throwable?) {
        if (canSave()) {
            Timber.d(t)
        }
    }

    override fun info(message: String?, vararg args: Any?) {
        if (canSave()) {
            Timber.i(message, args)
        }
    }

    override fun info(t: Throwable?, message: String?, vararg args: Any?) {
        if (canSave()) {
            Timber.i(t, message, args)
        }
    }

    override fun info(t: Throwable?) {
        if (canSave()) {
            Timber.i(t)
        }
    }

    override fun warning(message: String?, vararg args: Any?) {
        if (canSave()) {
            Timber.w(message, args)
        }
    }

    override fun warning(t: Throwable?, message: String?, vararg args: Any?) {
        if (canSave()) {
            Timber.w(t, message, args)
        }
    }

    override fun warning(t: Throwable?) {
        if (canSave()) {
            Timber.w(t)
        }
    }

    override fun error(message: String?, vararg args: Any?) {
        if (canSave()) {
            Timber.e(message, args)
        }
    }

    override fun error(t: Throwable?, message: String?, vararg args: Any?) {
        if (canSave()) {
            Timber.e(t, message, args)
        }
    }

    override fun error(t: Throwable?) {
        if (canSave()) {
            Timber.e(t)
        }
    }

}