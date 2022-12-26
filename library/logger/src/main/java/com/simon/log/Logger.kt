package com.simon.log

import android.annotation.SuppressLint

/**
 * @author Simon
 * @date 2020/5/1
 * @desc
 */
class Logger private constructor() {

    init {
        throw AssertionError()
    }

    companion object Log : ILog {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var logProvider: LogProvider = LoggerManager.logProvider()

        override fun verbose(message: String?, vararg args: Any?) {
            logProvider.provideLog().verbose(message, *args)
        }

        override fun verbose(t: Throwable?, message: String?, vararg args: Any?) {
            logProvider.provideLog().verbose(t, message, *args)
        }

        override fun verbose(t: Throwable?) {
            logProvider.provideLog().verbose(t)
        }

        override fun debug(message: String?, vararg args: Any?) {
            logProvider.provideLog().debug(message, *args)
        }

        override fun debug(t: Throwable?, message: String?, vararg args: Any?) {
            logProvider.provideLog().debug(t, message, *args)
        }

        override fun debug(t: Throwable?) {
            logProvider.provideLog().debug(t)
        }

        override fun info(message: String?, vararg args: Any?) {
            logProvider.provideLog().info(message, *args)
        }

        override fun info(t: Throwable?, message: String?, vararg args: Any?) {
            logProvider.provideLog().info(t, message, *args)
        }

        override fun info(t: Throwable?) {
            logProvider.provideLog().info(t)
        }

        override fun warning(message: String?, vararg args: Any?) {
            logProvider.provideLog().warning(message, *args)
        }

        override fun warning(t: Throwable?, message: String?, vararg args: Any?) {
            logProvider.provideLog().warning(t, message, *args)
        }

        override fun warning(t: Throwable?) {
            logProvider.provideLog().warning(t)
        }

        override fun error(message: String?, vararg args: Any?) {
            logProvider.provideLog().error(message, *args)
        }

        override fun error(t: Throwable?, message: String?, vararg args: Any?) {
            logProvider.provideLog().error(t, message, *args)
        }

        override fun error(t: Throwable?) {
            logProvider.provideLog().error(t)
        }
    }

}