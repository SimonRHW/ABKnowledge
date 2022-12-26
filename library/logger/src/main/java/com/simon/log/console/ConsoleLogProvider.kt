package com.simon.log.console

import com.simon.log.ILog
import com.simon.log.LogProvider
import com.simon.log.LoggerManager

class ConsoleLogProvider(
    private val consoleLog: Boolean = LoggerManager.enableLog,
    private val logLevel: Int = LoggerManager.logLevel,
    private val globalTag: String = "Slog",
) : LogProvider {

    override fun provideLog(): ILog {

        return ConsoleLog.getInstance(
            consoleLog = consoleLog,
            logLevel = logLevel,
            globalTag = globalTag
        )
    }
}