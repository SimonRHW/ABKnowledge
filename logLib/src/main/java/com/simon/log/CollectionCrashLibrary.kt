package com.simon.log

/**
 * @author Simon
 * @date 2020/5/1
 * @desc 将日志实时上传到服务端
 */
class CollectionCrashLibrary private constructor() {

    companion object {

        fun logWarning(t: Throwable?) {
            // TODO report non-fatal warning. firebase upload
        }

        fun logError(t: Throwable?) {
            // TODO report non-fatal warning. firebase upload
        }

    }

    init {
        throw AssertionError("No instances.")
    }
}