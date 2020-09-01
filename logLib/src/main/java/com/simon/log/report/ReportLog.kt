package com.simon.log.report

import com.simon.log.ILog
import timber.log.Timber

/**
 * @author Simon
 * @date 2020/5/1
 * @desc
 */
class ReportLog private constructor(private var isShowLog: Boolean, private var host: String) :
    ILog {

    companion object {
        @Volatile
        private var instance: ReportLog? = null

        fun getInstance(
            isShowLog: Boolean,
            host: String,
            logLevel: Int
        ) =
            instance ?: synchronized(this) {
                instance ?: ReportLog(
                    isShowLog,
                    host
                ).also {
                    instance = it
                    Timber.plant(ReportTree(logLevel))
                }
            }
    }

    private fun verifyHost(): Boolean {
        return this.host.isNotEmpty()
    }

    /**
     * 是否实时上报日志
     */
    private fun canReport(): Boolean {
        return verifyHost() && isShowLog
    }

    override fun verbose(message: String?, vararg args: Any?) {
        if (canReport()) {
            Timber.v(message, args)
        }
    }

    override fun verbose(t: Throwable?, message: String?, vararg args: Any?) {
        if (canReport()) {
            Timber.v(t, message, args)
        }
    }

    override fun verbose(t: Throwable?) {
        if (canReport()) {
            Timber.v(t)
        }
    }

    override fun debug(message: String?, vararg args: Any?) {
        if (canReport()) {
            Timber.d(message, args)
        }
    }

    override fun debug(t: Throwable?, message: String?, vararg args: Any?) {
        if (canReport()) {
            Timber.d(t, message, args)
        }
    }

    override fun debug(t: Throwable?) {
        if (canReport()) {
            Timber.d(t)
        }
    }

    override fun info(message: String?, vararg args: Any?) {
        if (canReport()) {
            Timber.i(message, args)
        }
    }

    override fun info(t: Throwable?, message: String?, vararg args: Any?) {
        if (canReport()) {
            Timber.i(t, message, args)
        }
    }

    override fun info(t: Throwable?) {
        if (canReport()) {
            Timber.i(t)
        }
    }

    override fun warning(message: String?, vararg args: Any?) {
        if (canReport()) {
            Timber.w(message, args)
        }
    }

    override fun warning(t: Throwable?, message: String?, vararg args: Any?) {
        if (canReport()) {
            Timber.w(t, message, args)
        }
    }

    override fun warning(t: Throwable?) {
        if (canReport()) {
            Timber.w(t)
        }
    }

    override fun error(message: String?, vararg args: Any?) {
        if (canReport()) {
            Timber.e(message, args)
        }
    }

    override fun error(t: Throwable?, message: String?, vararg args: Any?) {
        if (canReport()) {
            Timber.e(t, message, args)
        }
    }

    override fun error(t: Throwable?) {
        if (canReport()) {
            Timber.e(t)
        }
    }
}