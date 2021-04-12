package com.simon.log.report

import android.util.Log
import com.simon.log.CollectionCrashLibrary
import timber.log.Timber
import kotlin.math.min

/**
 * @author Simon
 * @date 2020/5/1
 * @desc
 */
open class ReportTree(private val logLevel: Int, private val logService: LogService?) : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority < logLevel) {
            return
        }
        logService?.uploadLog(message, priority)?.let {
            //没有service,输出收集日常日志
            var i = 0
            val length = message.length
            while (i < length) {
                var newline = message.indexOf('\n', i)
                newline = if (newline != -1) newline else length
                do {
                    val end = min(newline, i + MAX_LOG_LENGTH)
                    val part = message.substring(i, end)
                    when (priority) {
                        Log.VERBOSE -> Log.v(tag, part)
                        Log.DEBUG -> Log.d(tag, part)
                        Log.INFO -> Log.i(tag, part)
                        Log.WARN -> {
                            Log.w(tag, part)
                            CollectionCrashLibrary.logWarning(t)
                        }
                        Log.ERROR -> {
                            Log.e(tag, part)
                            CollectionCrashLibrary.logError(t)
                        }
                        Log.ASSERT -> return
                    }
                    i = end
                } while (i < newline)
                i++
            }
        }
    }

    companion object {
        private const val MAX_LOG_LENGTH = 4000
    }
}
