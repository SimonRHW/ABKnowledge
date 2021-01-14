package com.simon.log.local

/**
 * @author Simon
 * @date 2020/5/1
 * @desc 警告错误预警
 */
class LocalStorageLibrary private constructor() {

    companion object {

        fun logWarning(t: Throwable?) {
            // TODO report non-fatal warning.
        }

        fun logError(t: Throwable?) {
            // TODO report non-fatal error.
        }
    }

    init {
        throw AssertionError("No instances.")
    }
}