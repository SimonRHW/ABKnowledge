package com.simon.log.local


import com.simon.log.BaseLog
import com.simon.log.CollectionCrashLibrary
import com.simon.log.Logger
import com.tencent.mars.xlog.Log
import timber.log.Timber
import java.util.regex.Pattern
import kotlin.math.min

/**
 * @author Simon
 * @date 2020/5/1
 * @desc
 */
open class LocalStorageTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        var i = 0
        val length = message.length
        while (i < length) {
            var newline = message.indexOf('\n', i)
            newline = if (newline != -1) newline else length
            do {
                val end = min(newline, i + MAX_LOG_LENGTH)
                val part = message.substring(i, end)
                when (priority - 2) {
                    Log.LEVEL_VERBOSE -> Log.v(tag, part)
                    Log.LEVEL_DEBUG -> Log.d(tag, part)
                    Log.LEVEL_INFO -> Log.i(tag, part)
                    Log.LEVEL_WARNING -> {
                        Log.w(tag, part)
                        CollectionCrashLibrary.logWarning(t)
                    }

                    Log.LEVEL_ERROR -> {
                        Log.e(tag, part)
                        CollectionCrashLibrary.logError(t)
                    }

                    Log.LEVEL_FATAL, Log.LEVEL_NONE -> return
                }
                i = end
            } while (i < newline)
            i++
        }
    }

    override val tag: String?
        get() = super.tag ?: Throwable().stackTrace
            .first { it.className !in fqcnIgnore }
            .let(::createStackElementTag)

    private val fqcnIgnore = listOf(
        Timber::class.java.name,
        Timber.Forest::class.java.name,
        Timber.Tree::class.java.name,
        BaseLog::class.java.name,
        LocalStorageLog::class.java.name,
        LocalStorageTree::class.java.name,
        Logger.Log::class.java.name,
        Logger::class.java.name
    )

    companion object {
        private const val MAX_LOG_LENGTH = 4000
        private const val MAX_TAG_LENGTH = 23
        private val ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$")
    }

    protected open fun createStackElementTag(element: StackTraceElement): String? {
        var tag = element.className.substringAfterLast('.')
        val m = ANONYMOUS_CLASS.matcher(tag)
        if (m.find()) {
            tag = m.replaceAll("")
        }
        // Tag length limit was removed in API 24.
        return if (true) {
            tag
        } else {
            tag.substring(0, MAX_TAG_LENGTH)
        }
    }

}