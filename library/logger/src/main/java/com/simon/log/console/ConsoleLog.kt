package com.simon.log.console

import com.simon.log.BaseLog
import timber.log.Timber

/**
 * @author Simon
 * @date 2020/5/1
 * @desc:
 */
class ConsoleLog private constructor(
    consoleLog: Boolean,
    logLevel: Int,
) : BaseLog(consoleLog, logLevel) {

    companion object {
        @Volatile
        private var instance: ConsoleLog? = null

        fun getInstance(
            consoleLog: Boolean,
            globalTag: String,
            logLevel: Int,
        ) =
            instance ?: synchronized(this) {
                instance ?: ConsoleLog(
                    consoleLog,
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