package com.simon.log.report

import com.simon.log.BaseLog
import timber.log.Timber

/**
 * @author Simon
 * @date 2020/5/1
 * @desc
 */
class ReportLog private constructor(
    consoleLog: Boolean,
    logLevel: Int
) : BaseLog(consoleLog, logLevel) {

    companion object {
        @Volatile
        private var instance: ReportLog? = null

        fun getInstance(
            consoleLog: Boolean,
            logLevel: Int,
            logService: LogService?
        ) =
            instance ?: synchronized(this) {
                instance ?: ReportLog(
                    consoleLog,
                    logLevel
                ).also {
                    instance = it
                    Timber.plant(ReportTree(logLevel,logService))
                }
            }
    }
}