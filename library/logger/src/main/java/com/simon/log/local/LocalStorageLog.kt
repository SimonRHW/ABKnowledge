package com.simon.log.local


import com.simon.log.BaseLog
import timber.log.Timber

/**
 * @author Simon
 * @date 2020/5/1
 * @desc
 */
class LocalStorageLog private constructor(
    consoleLog: Boolean,
    logLevel: Int
) : BaseLog(consoleLog, logLevel) {

    companion object {
        @Volatile
        private var instance: LocalStorageLog? = null

        fun getInstance(
            consoleLog: Boolean,
            logLevel: Int
        ) =
            instance ?: synchronized(this) {
                instance ?: LocalStorageLog(
                    consoleLog,
                    logLevel
                ).also {
                    instance = it
                    Timber.plant(LocalStorageTree())
                }
            }
    }

}