package com.simon.log.report

import com.simon.log.ILog
import com.simon.log.LogProvider
import com.simon.log.LoggerManager

class ReportLogProvider(
    private val consoleLog: Boolean = LoggerManager.enableLog,
    private val logLevel: Int = LoggerManager.logLevel,
    private val logService: LogService
) : LogProvider {

    override fun provideLog(): ILog {
        return ReportLog.getInstance(
            consoleLog = consoleLog,
            logLevel = logLevel,
            logService = logService
        )
    }
}