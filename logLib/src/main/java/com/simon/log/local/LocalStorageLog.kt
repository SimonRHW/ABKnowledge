package com.simon.log.local


import com.simon.log.BaseLog
import timber.log.Timber

/**
 * @author Simon
 * @date 2020/5/1
 * @desc
 */
class LocalStorageLog private constructor(
    private var isShowLog: Boolean,
    private var logLevel: Int
) : BaseLog(isShowLog, logLevel) {

    companion object {
        @Volatile
        private var instance: LocalStorageLog? = null

        fun getInstance(
            isShowLog: Boolean,
            globalTag: String,
            logLevel: Int
        ) =
            instance ?: synchronized(this) {
                instance ?: LocalStorageLog(
                    isShowLog,
                    logLevel
                ).also {
                    instance = it
                    Timber.plant(LocalStorageTree(globalTag))
                }
            }
    }

}