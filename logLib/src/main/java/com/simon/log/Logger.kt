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
        private var instance: LoggerManager? = null

        internal fun setLogManager(logManager: LoggerManager) {
            instance = logManager
        }

        private fun checkLogManagerNotNull() {
            if (instance == null) {
                throw  ExceptionInInitializerError("请先在全局Application将LogManager初始化")
            }
        }

        override fun verbose(message: String?, vararg args: Any?) {
            checkLogManagerNotNull()
            instance!!.provideLogImpl().verbose(message, args)
        }

        override fun verbose(t: Throwable?, message: String?, vararg args: Any?) {
            checkLogManagerNotNull()
            instance!!.provideLogImpl().verbose(t, message, args)
        }

        override fun verbose(t: Throwable?) {
            checkLogManagerNotNull()
            instance!!.provideLogImpl().verbose(t)
        }

        override fun debug(message: String?, vararg args: Any?) {
            checkLogManagerNotNull()
            instance!!.provideLogImpl().debug(message, args)
        }

        override fun debug(t: Throwable?, message: String?, vararg args: Any?) {
            checkLogManagerNotNull()
            instance!!.provideLogImpl().debug(t, message, args)
        }

        override fun debug(t: Throwable?) {
            checkLogManagerNotNull()
            instance!!.provideLogImpl().debug(t)
        }

        override fun info(message: String?, vararg args: Any?) {
            checkLogManagerNotNull()
            instance!!.provideLogImpl().info(message, args)
        }

        override fun info(t: Throwable?, message: String?, vararg args: Any?) {
            checkLogManagerNotNull()
            instance!!.provideLogImpl().info(t, message, args)
        }

        override fun info(t: Throwable?) {
            checkLogManagerNotNull()
            instance!!.provideLogImpl().info(t)
        }

        override fun warning(message: String?, vararg args: Any?) {
            checkLogManagerNotNull()
            instance!!.provideLogImpl().warning(message, args)
        }

        override fun warning(t: Throwable?, message: String?, vararg args: Any?) {
            checkLogManagerNotNull()
            instance!!.provideLogImpl().warning(t, message, args)
        }

        override fun warning(t: Throwable?) {
            checkLogManagerNotNull()
            instance!!.provideLogImpl().warning(t)
        }

        override fun error(message: String?, vararg args: Any?) {
            checkLogManagerNotNull()
            instance!!.provideLogImpl().error(message, args)
        }

        override fun error(t: Throwable?, message: String?, vararg args: Any?) {
            checkLogManagerNotNull()
            instance!!.provideLogImpl().error(t, message, args)
        }

        override fun error(t: Throwable?) {
            checkLogManagerNotNull()
            instance!!.provideLogImpl().error(t)
        }
    }

}