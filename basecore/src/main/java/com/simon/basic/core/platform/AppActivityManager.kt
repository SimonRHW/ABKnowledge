package com.simon.basic.core.platform

import android.app.Activity
import android.os.Process
import java.util.*
import kotlin.system.exitProcess

/**
 * @author Simon
 * @date 2020/4/12
 * @desc 在Application中用registerActivityLifecycleCallbacks进行Activity生命周期的栈管理
 */
class AppActivityManager private constructor() {

    companion object {
        internal val appManager: AppActivityManager
            get() = Holder.INSTANCE
    }

    /**
     * activity存储栈
     */
    private var sActivityStack: ArrayDeque<Activity>? = null

    /**
     * 单一实例
     */
    private object Holder {
        val INSTANCE = AppActivityManager()
    }

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        if (sActivityStack == null) {
            sActivityStack = ArrayDeque()
        }
        sActivityStack!!.push(activity)
    }

    /**
     * 移除指定的Activity
     *
     * @param activity
     */
    fun removeActivity(activity: Activity?) {
        if (sActivityStack == null) {
            return
        }
        if (activity != null) {
            sActivityStack!!.remove(activity)
        }
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishLastActivity() {
        if (sActivityStack != null && !sActivityStack!!.isEmpty()) {
            val activity = sActivityStack!!.pop()
            finishActivity(activity)
        }
    }

    /**
     * 结束指定的Activity
     */
    private fun finishActivity(activity: Activity?) {
        if (sActivityStack == null) {
            return
        }
        if (activity != null && !activity.isFinishing) {
            sActivityStack!!.remove(activity)
            activity.finish()
        }
    }


    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        if (sActivityStack == null) {
            return
        }
        for (activity in sActivityStack!!) {
            if (activity.javaClass == cls) {
                finishActivity(activity)
            }
        }
    }

    /**
     * 结束所有Activity
     */
    private fun finishAllActivity() {
        if (sActivityStack == null) {
            return
        }
        while (!sActivityStack!!.isEmpty()) {
            val activity = sActivityStack!!.pop()
            activity.finish()
        }
    }

    /**
     * 退出应用程序
     */
    fun appExit(isBackground: Boolean?) {
        try {
            //finish所有activity
            finishAllActivity()
            //杀死进程
            Process.killProcess(Process.myPid())
        } catch (ignored: Exception) {
        } finally {
            if (!isBackground!!) {
                exitProcess(0)
            }
        }
    }

}