package com.simon.basic.core.platform

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import com.simon.log.Logger
import java.lang.ref.WeakReference
import java.util.*

/**
 * @author Simon
 * @date 2020/4/12
 * @desc 应用状态管理类
 */
class AppStatusManager private constructor() : ActivityLifecycleCallbacks {

    private var activityCount = 0
    private var mCurrentActivity: WeakReference<Activity>? = null
    private lateinit var appActivityManager: AppActivityManager

    companion object {

        /**
         * 缓存需要监听应用状态的页面
         */
        private val appBackgroundObserverSet: MutableSet<AppBackgroundObserver> = HashSet()

        private val appActivityManager: AppActivityManager? = null

        val instance: AppStatusManager
            get() = Holder.INSTANCE
    }

    interface AppBackgroundObserver {
        /**
         * 应用进入后台状态变更回调
         */
        fun intoBackgroundStatus()
    }

    /**
     * @return App 是否在前台
     * @see .isAppBackground
     */
    private var isAppForeground = false

    private object Holder {
        val INSTANCE = AppStatusManager()
    }

    fun init(app: Application) {
        appActivityManager = AppActivityManager()
        app.registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(
        activity: Activity,
        savedInstanceState: Bundle?
    ) {
        Logger.info("onActivityCreated: current activity =" + activity.localClassName)
        appActivityManager.addActivity(activity)
    }

    override fun onActivityStarted(activity: Activity) {
        Logger.info("onActivityStarted: current activity =" + activity.localClassName)
        activityCount++
        isAppForeground = true
    }

    override fun onActivityResumed(activity: Activity) {
        mCurrentActivity = WeakReference(activity)
        Logger.info("onActivityResumed: current activity =" + activity.localClassName)
    }

    override fun onActivityPaused(activity: Activity) {
        Logger.info("onActivityPaused: current activity =" + activity.localClassName)
        if (mCurrentActivity != null) {
            mCurrentActivity?.clear()
            mCurrentActivity = null
        }
    }

    override fun onActivityStopped(activity: Activity) {
        Logger.info("onActivityStopped: current activity =" + activity.localClassName)
        activityCount--
        if (activityCount == 0) {
            isAppForeground = false
            Logger.info("onActivityStopped: App进入后台")
            if (appBackgroundObserverSet.isNotEmpty()) {
                for (backgroundObserver in appBackgroundObserverSet) {
                    backgroundObserver.intoBackgroundStatus()
                }
            }
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        Logger.info("onActivitySaveInstanceState: current activity =" + activity.localClassName)
    }

    override fun onActivityDestroyed(activity: Activity) {
        Logger.info("onActivityDestroyed: current activity =" + activity.localClassName)
        appActivityManager.removeActivity(activity)
    }

    /**
     * @return App 是否在后台
     * @see .isAppForeground
     *
     */
    val isAppBackground: Boolean
        get() = !isAppForeground

    /**
     * 获取当前显示的activity
     * @return 当前resume的activity
     */
    val currentResumeActivity: Activity?
        get() = mCurrentActivity?.get()

    fun addObserver(observer: AppBackgroundObserver) {
        appBackgroundObserverSet.add(observer)
    }

    fun removeObserver(observer: AppBackgroundObserver) {
        appBackgroundObserverSet.remove(observer)
    }

    /**
     * 退出应用程序
     * @param isBackground 是否保持后台运行
     */
    fun appExit(isBackground: Boolean?) {
        appActivityManager.appExit(isBackground)
    }

}