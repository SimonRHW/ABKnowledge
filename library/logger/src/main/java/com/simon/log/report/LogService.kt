package com.simon.log.report

/**
 * @author Simon
 * @date 2021/4/11
 * @time 4:01 PM
 * @desc
 */
interface LogService {
    /**
     * @param message 日志信息
     * @param type 日志类型、包括正常、异常的日志
     */
    fun uploadLog(message: String, type: Int)
}