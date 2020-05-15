package com.simon.basic.core.util

import android.app.ActivityManager
import android.content.Context
import android.os.Process


/**
 * @author Simon
 * @date 2020/5/10
 * @desc
 */
object ProcessUtil {
    /**
     * @return null may be returned if the specified process not found
     */
    fun getProcessName(cxt: Context, pid: Int): String? {
        val am = cxt.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningApps =
            am.runningAppProcesses ?: return null
        for (procInfo in runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName
            }
        }
        return null
    }

    fun getMyProcessId(): Int {
        return Process.myPid();
    }
}