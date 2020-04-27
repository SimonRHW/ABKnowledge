package com.simon.log.local


import com.tencent.mars.xlog.Log
import timber.log.Timber
import kotlin.math.min

/**
 * @author Simon
 * @date 2020/5/1
 * @desc
 */
open class LocalStorageTree : Timber.Tree() {

    companion object {
        private const val MAX_LOG_LENGTH = 4000
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (message.length < MAX_LOG_LENGTH) {
            when (priority) {
                Log.LEVEL_VERBOSE -> Log.v(tag, message)
                Log.LEVEL_DEBUG -> Log.d(tag, message)
                Log.LEVEL_INFO -> Log.i(tag, message)
                Log.LEVEL_WARNING -> Log.w(tag, message)
                Log.LEVEL_ERROR -> Log.e(tag, message)
                Log.LEVEL_FATAL, Log.LEVEL_NONE -> return
            }
            return
        }

        var i = 0
        val length = message.length
        while (i < length) {
            var newline = message.indexOf('\n', i)
            newline = if (newline != -1) newline else length
            do {
                val end = min(newline, i + MAX_LOG_LENGTH)
                val part = message.substring(i, end)
                when (priority) {
                    Log.LEVEL_VERBOSE -> Log.v(tag, part)
                    Log.LEVEL_DEBUG -> Log.d(tag, part)
                    Log.LEVEL_INFO -> Log.i(tag, part)
                    Log.LEVEL_WARNING -> {
                        Log.w(tag, part)
                        LocalStorageLibrary.logWarning(t)
                    }
                    Log.LEVEL_ERROR -> {
                        Log.e(tag, part)
                        LocalStorageLibrary.logError(t)
                    }
                    Log.LEVEL_FATAL, Log.LEVEL_NONE -> return
                }
                i = end
            } while (i < newline)
            i++
        }
    }

}