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
    fun getProcessName(cxt: Context, pid: Int): String {
        val am = cxt.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningApps =
            am.runningAppProcesses ?: return ""
        for (runningAppProcessInfo in runningApps) {
            if (runningAppProcessInfo.pid == pid) {
                return runningAppProcessInfo.processName
            }
        }
        return ""
    }

    fun getMyProcessId(): Int {
        return Process.myPid();
    }
}