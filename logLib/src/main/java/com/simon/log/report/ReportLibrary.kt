package com.simon.log.report

/**
 * @author Simon
 * @date 2020/5/1
 * @desc 将日志上传到服务端
 */
class ReportLibrary private constructor() {

    companion object {

        fun log(priority: Int, tag: String?, message: String?) {
            // TODO add log entry to circular buffer.
        }

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