package com.simon.log

import timber.log.Timber

/**
 * @author Simon
 * @date 2021/1/10
 */
abstract class BaseLog internal constructor(
    private var isShowLog: Boolean,
    private var logLevel: Int
) : ILog {

    companion object {
        const val LEVEL_ALL = 0
        const val LEVEL_VERBOSE = 0
        const val LEVEL_DEBUG = 1
        const val LEVEL_INFO = 2
        const val LEVEL_WARNING = 3
        const val LEVEL_ERROR = 4
        const val LEVEL_NONE = 5
    }

    private fun checkPrintOut(level: Int): Boolean {
        return isShowLog && logLevel <= level
    }

    override fun verbose(message: String?, vararg args: Any?) {
        if (checkPrintOut(LEVEL_VERBOSE)) {
            Timber.v(message, *args)
        }
    }

    override fun verbose(t: Throwable?, message: String?, vararg args: Any?) {
        if (checkPrintOut(LEVEL_VERBOSE)) {
            Timber.v(t, message, *args)
        }
    }

    override fun verbose(t: Throwable?) {
        if (checkPrintOut(LEVEL_VERBOSE)) {
            Timber.v(t)
        }
    }

    override fun debug(message: String?, vararg args: Any?) {
        if (checkPrintOut(LEVEL_DEBUG)) {
            Timber.d(message, *args)
        }
    }

    override fun debug(t: Throwable?, message: String?, vararg args: Any?) {
        if (checkPrintOut(LEVEL_DEBUG)) {
            Timber.d(t, message, *args)
        }
    }

    override fun debug(t: Throwable?) {
        if (checkPrintOut(LEVEL_DEBUG)) {
            Timber.d(t)
        }
    }

    override fun info(message: String?, vararg args: Any?) {
        if (checkPrintOut(LEVEL_INFO)) {
            Timber.i(message, *args)
        }
    }

    override fun info(t: Throwable?, message: String?, vararg args: Any?) {
        if (checkPrintOut(LEVEL_INFO)) {
            Timber.i(t, message, *args)
        }
    }

    override fun info(t: Throwable?) {
        if (checkPrintOut(LEVEL_INFO)) {
            Timber.i(t)
        }
    }

    override fun warning(message: String?, vararg args: Any?) {
        if (checkPrintOut(LEVEL_WARNING)) {
            Timber.w(message, *args)
        }
    }

    override fun warning(t: Throwable?, message: String?, vararg args: Any?) {
        if (checkPrintOut(LEVEL_WARNING)) {
            Timber.w(t, message, *args)
        }
    }

    override fun warning(t: Throwable?) {
        if (checkPrintOut(LEVEL_WARNING)) {
            Timber.w(t)
        }
    }

    override fun error(message: String?, vararg args: Any?) {
        if (checkPrintOut(LEVEL_ERROR)) {
            Timber.e(message, *args)
        }
    }

    override fun error(t: Throwable?, message: String?, vararg args: Any?) {
        if (checkPrintOut(LEVEL_ERROR)) {
            Timber.e(t, message, *args)
        }
    }

    override fun error(t: Throwable?) {
        if (checkPrintOut(LEVEL_ERROR)) {
            Timber.e(t)
        }
    }
}