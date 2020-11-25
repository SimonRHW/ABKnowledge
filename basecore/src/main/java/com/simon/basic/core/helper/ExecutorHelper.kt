package com.simon.basic.core.helper

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * @author Simon
 * @date 2020/11/2
 * @desc 线程池帮助类
 */

object ExecutorHelper {

    private val MUTEX_LOCK = Any()

    @Volatile
    private var sBigOnlineTaskExecutor: ThreadPoolExecutor? = null

    @Volatile
    private var sLocalExecutor: ThreadPoolExecutor? = null

    @Volatile
    private var sSerialExecutor: ThreadPoolExecutor? = null

    @Volatile
    private var sSmallOnlineTaskExecutor: ThreadPoolExecutor? = null

    @JvmStatic
    fun getLocalTaskExecutor(): ThreadPoolExecutor? {
        if (sLocalExecutor == null) {
            synchronized(MUTEX_LOCK) {
                if (sLocalExecutor == null) {
                    val threadPoolExecutor: ThreadPoolExecutor =
                        ThreadPoolExecutor(
                            3,
                            3,
                            60,
                            TimeUnit.SECONDS,
                            LinkedBlockingQueue<Runnable>(),
                            ThreadFactory { runnable -> Thread(runnable, "LocalLoadDataTask") })
                    threadPoolExecutor.allowCoreThreadTimeOut(true)
                    sLocalExecutor = threadPoolExecutor
                }
            }
        }
        return sLocalExecutor
    }

    @JvmStatic
    fun getSerialTaskExecutor(): ThreadPoolExecutor? {
        if (sSerialExecutor == null) {
            synchronized(MUTEX_LOCK) {
                if (sSerialExecutor == null) {
                    val threadPoolExecutor: ThreadPoolExecutor =
                        ThreadPoolExecutor(
                            1,
                            1,
                            60,
                            TimeUnit.SECONDS,
                            LinkedBlockingQueue<Runnable>(),
                            ThreadFactory { runnable -> Thread(runnable, "SerialLoadDataTask") })
                    threadPoolExecutor.allowCoreThreadTimeOut(true)
                    sSerialExecutor = threadPoolExecutor
                }
            }
        }
        return sSerialExecutor
    }

    @JvmStatic
    fun getShortOnlineTaskExecutor(): ThreadPoolExecutor? {
        if (sSmallOnlineTaskExecutor == null) {
            synchronized(MUTEX_LOCK) {
                if (sSmallOnlineTaskExecutor == null) {
                    val threadPoolExecutor: ThreadPoolExecutor =
                        ThreadPoolExecutor(
                            3,
                            3,
                            60,
                            TimeUnit.SECONDS,
                            LinkedBlockingQueue<Runnable>(),
                            ThreadFactory { runnable ->
                                Thread(
                                    runnable,
                                    "ShortOnlineLoadDataTask"
                                )
                            })
                    threadPoolExecutor.allowCoreThreadTimeOut(true)
                    sSmallOnlineTaskExecutor = threadPoolExecutor
                }
            }
        }
        return sSmallOnlineTaskExecutor
    }

    @JvmStatic
    fun getLongOnlineTaskExecutor(): ThreadPoolExecutor? {
        if (sBigOnlineTaskExecutor == null) {
            synchronized(MUTEX_LOCK) {
                if (sBigOnlineTaskExecutor == null) {
                    val threadPoolExecutor =
                        ThreadPoolExecutor(
                            5,
                            5,
                            60,
                            TimeUnit.SECONDS,
                            LinkedBlockingQueue<Runnable>(),
                            ThreadFactory { runnable ->
                                Thread(
                                    runnable,
                                    "LongOnlineLoadDataTask"
                                )
                            })
                    threadPoolExecutor.allowCoreThreadTimeOut(true)
                    sBigOnlineTaskExecutor = threadPoolExecutor
                }
            }
        }
        return sBigOnlineTaskExecutor
    }

    fun executeOnLocalTaskExecutor(runnable: Runnable?) {
        getLocalTaskExecutor()!!.execute(runnable)
    }

    fun executeSerialTaskExecutor(runnable: Runnable?) {
        getSerialTaskExecutor()!!.execute(runnable)
    }

    fun executeOnShortOnlineTaskExecutor(runnable: Runnable?) {
        getShortOnlineTaskExecutor()!!.execute(runnable)
    }

    fun executeOnLongOnlineTaskExecutor(runnable: Runnable?) {
        getLongOnlineTaskExecutor()!!.execute(runnable)
    }
}