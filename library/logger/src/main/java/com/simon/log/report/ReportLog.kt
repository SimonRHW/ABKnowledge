package com.simon.log.report

import com.simon.log.BaseLog
import timber.log.Timber

/**
 * @author Simon
 * @date 2020/5/1
 * @desc
 */
class ReportLog private constructor(
    isShowLog: Boolean,
    logLevel: Int
) : BaseLog(isShowLog, logLevel) {

    companion object {
        @Volatile
        private var instance: ReportLog? = null

        fun getInstance(
            isShowLog: Boolean,
            logLevel: Int,
            logService: LogService?
        ) =
            instance ?: synchronized(this) {
                instance ?: ReportLog(
                    isShowLog,
                    logLevel
                ).also {
                    instance = it
                    Timber.plant(ReportTree(logLevel,logService))
                }
            }
    }
}