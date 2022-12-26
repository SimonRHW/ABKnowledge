package com.simon.log

import org.jetbrains.annotations.NonNls

/**
 * @author Simon
 * @date 2020/5/1
 * @desc
 */

interface LogProvider {
    fun provideLog(): ILog
}

interface ILog {

    fun verbose(@NonNls message: String?, vararg args: Any?)

    fun verbose(t: Throwable?, @NonNls message: String?, vararg args: Any?)

    fun verbose(t: Throwable?)

    fun debug(@NonNls message: String?, vararg args: Any?)

    fun debug(t: Throwable?, @NonNls message: String?, vararg args: Any?)

    fun debug(t: Throwable?)

    fun info(@NonNls message: String?, vararg args: Any?)

    fun info(t: Throwable?, @NonNls message: String?, vararg args: Any?)

    fun info(t: Throwable?)

    fun warning(@NonNls message: String?, vararg args: Any?)

    fun warning(t: Throwable?, @NonNls message: String?, vararg args: Any?)

    fun warning(t: Throwable?)

    fun error(@NonNls message: String?, vararg args: Any?)

    fun error(t: Throwable?, @NonNls message: String?, vararg args: Any?)

    fun error(t: Throwable?)

}