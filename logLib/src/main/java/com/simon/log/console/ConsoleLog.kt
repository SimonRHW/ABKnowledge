package com.simon.log.console

import com.simon.log.ILog
import timber.log.Timber

/**
 * @author Simon
 * @date 2020/5/1
 * @desc:
 */
class ConsoleLog private constructor(
    private var isShowLog: Boolean,
    private var globalTag: String
) : ILog {

    companion object {
        @Volatile
        private var instance: ConsoleLog? = null

        fun getInstance(
            isShowLog: Boolean,
            globalTag: String
        ) =
            instance ?: synchronized(this) {
                instance ?: ConsoleLog(
                    isShowLog,
                    globalTag
                ).also {
                    instance = it
                    val debugTree = Timber.DebugTree()
                    debugTree.globalTag = globalTag
                    Timber.plant(debugTree)
                }
            }
    }

    override fun verbose(message: String?, vararg args: Any?) {
        if (isShowLog) {
            Timber.v(globalTag + message, args)
        }
    }

    override fun verbose(t: Throwable?, message: String?, vararg args: Any?) {
        if (isShowLog) {
            Timber.v(t, message, args)
        }
    }

    override fun verbose(t: Throwable?) {
        if (isShowLog) {
            Timber.v(t)
        }
    }

    override fun debug(message: String?, vararg args: Any?) {
        if (isShowLog) {
            Timber.d(message, args)
        }
    }

    override fun debug(t: Throwable?, message: String?, vararg args: Any?) {
        if (isShowLog) {
            Timber.d(t, message, args)
        }
    }

    override fun debug(t: Throwable?) {
        if (isShowLog) {
            Timber.d(t)
        }
    }

    override fun info(message: String?, vararg args: Any?) {
        if (isShowLog) {
            Timber.i(message, args)
        }
    }

    override fun info(t: Throwable?, message: String?, vararg args: Any?) {
        if (isShowLog) {
            Timber.i(t, message, args)
        }
    }

    override fun info(t: Throwable?) {
        if (isShowLog) {
            Timber.i(t)
        }
    }

    override fun warning(message: String?, vararg args: Any?) {
        if (isShowLog) {
            Timber.w(message, args)
        }
    }

    override fun warning(t: Throwable?, message: String?, vararg args: Any?) {
        if (isShowLog) {
            Timber.w(t, message, args)
        }
    }

    override fun warning(t: Throwable?) {
        if (isShowLog) {
            Timber.w(t)
        }
    }

    override fun error(message: String?, vararg args: Any?) {
        if (isShowLog) {
            Timber.e(message, args)
        }
    }

    override fun error(t: Throwable?, message: String?, vararg args: Any?) {
        if (isShowLog) {
            Timber.e(t, message, args)
        }
    }

    override fun error(t: Throwable?) {
        if (isShowLog) {
            Timber.e(t)
        }
    }

}