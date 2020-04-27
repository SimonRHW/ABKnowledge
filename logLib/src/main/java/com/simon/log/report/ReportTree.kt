package com.simon.log.report

import android.util.Log
import timber.log.Timber

/**
 * @author Simon
 * @date 2020/5/1
 * @desc
 */
open class ReportTree(private val logLevel :Int) : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority < logLevel) {
            return
        }

        ReportLibrary.log(priority, tag, message)

        if (t != null) {
            if (priority == Log.ERROR) {
                ReportLibrary.logError(t)
            } else if (priority == Log.WARN) {
                ReportLibrary.logWarning(t)
            }
        }
    }
}
