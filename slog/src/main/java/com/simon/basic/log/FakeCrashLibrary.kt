package com.simon.basic.log

import com.tencent.mars.xlog.Log

/**
 * Not a real crash reporting library!
 */
class FakeCrashLibrary private constructor() {
    companion object {
        fun log(priority: Int, tag: String?, message: String?) {
            when (priority) {
                Log.LEVEL_VERBOSE -> Log.v(tag, message)
                Log.LEVEL_DEBUG -> Log.d(tag, message)
                Log.LEVEL_INFO -> Log.i(tag, message)
                Log.LEVEL_WARNING -> Log.w(tag, message)
                Log.LEVEL_ERROR -> Log.e(tag, message)
                Log.LEVEL_FATAL -> Log.f(tag, message)
                Log.LEVEL_NONE -> return
            }
        }

        fun logWarning(tag: String?, t: Throwable) {
            Log.w(tag, t.message)
        }

        fun logError(tag: String?, t: Throwable) {
            Log.e(tag, t.message)
        }
    }

    init {
        throw AssertionError("No instances.") as Throwable
    }
}