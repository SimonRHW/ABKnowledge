package com.simon.log.console

import com.simon.log.BaseLog
import timber.log.Timber

/**
 * @author Simon
 * @date 2020/5/1
 * @desc:
 */
class ConsoleLog private constructor(
    private var isShowLog: Boolean,
    private var logLevel: Int
) : BaseLog(isShowLog,logLevel){

    companion object {
        @Volatile
        private var instance: ConsoleLog? = null

        fun getInstance(
            isShowLog: Boolean,
            globalTag: String,
            logLevel: Int
        ) =
            instance ?: synchronized(this) {
                instance ?: ConsoleLog(
                    isShowLog,
                    logLevel
                ).also {
                    instance = it
                    val debugTree = Timber.DebugTree()
                    debugTree.globalTag = globalTag
                    Timber.plant(debugTree)
                }
            }
    }
}