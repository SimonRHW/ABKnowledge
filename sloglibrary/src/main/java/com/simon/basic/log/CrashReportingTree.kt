package com.simon.basic.log

import android.util.Log
import timber.log.Timber

open class CrashReportingTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }
        FakeCrashLibrary.log(priority, tag, message)
        if (t != null) {
            if (priority == Log.ERROR) {
                FakeCrashLibrary.logError(tag, t)
            } else if (priority == Log.WARN) {
                FakeCrashLibrary.logWarning(tag, t)
            }
        }
    }

}