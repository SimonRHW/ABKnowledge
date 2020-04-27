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

    companion object {

        /**
         * 缓存需要监听应用状态的页面
         */
        private val CALL_BACK_CACHE: MutableSet<BackGroundCallback> = HashSet()

        val instance: AppStatusManager
            get() = Holder.INSTANCE
    }

    /**
     * @return App 是否在前台
     * @see .isAppBackground
     */
    /**
     * 是否在前台
     */
    private var isAppForeground = false

    /**
     * 静态内部类的优点是：外部类加载时并不需要立即加载内部类，内部类不被加载则不去初始化INSTANCE，故而不占内存。
     * 即当AppStatusManager第一次被加载时，Holder，只有当getInstance()方法第一次被调用时，才会去初始化INSTANCE
     * 第一次调用getInstance()方法会导致虚拟机加载Holder类，这种方法不仅能确保线程安全，也能保证单例的唯一性，同时也延迟了单例的实例化。
     */
    private object Holder {
        val INSTANCE = AppStatusManager()
    }

    fun init(app: Application) {
        app.registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(
        activity: Activity,
        savedInstanceState: Bundle?
    ) {
        Logger.info("onActivityCreated: current activity =" + activity.localClassName)
        AppActivityManager.appManager.addActivity(activity)
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
            mCurrentActivity!!.clear()
            mCurrentActivity = null
        }
    }

    override fun onActivityStopped(activity: Activity) {
        Logger.info("onActivityStopped: current activity =" + activity.localClassName)
        activityCount--
        if (activityCount == 0) {
            isAppForeground = false
            Logger.info("onActivityStopped: App进入后台")
            if (CALL_BACK_CACHE.isNotEmpty()) {
                for (backGroundCallback in CALL_BACK_CACHE) {
                    backGroundCallback.onAppStatusChange()
                }
            }
        }
    }

    override fun onActivitySaveInstanceState(
        activity: Activity,
        outState: Bundle?
    ) {
        Logger.info("onActivitySaveInstanceState: current activity =" + activity.localClassName)
    }

    override fun onActivityDestroyed(activity: Activity) {
        Logger.info("onActivityDestroyed: current activity =" + activity.localClassName)
        AppActivityManager.appManager.removeActivity(activity)
    }

    /**
     * @return App 是否在后台
     * @see .isAppForeground
     */
    val isAppBackground: Boolean
        get() = !isAppForeground

    /**
     * 获取当前显示的activity
     * @return 当前resume的activity
     */
    val currentActivity: Activity?
        get() = mCurrentActivity!!.get()

    fun addObserver(observer: BackGroundCallback) {
        CALL_BACK_CACHE.add(observer)
    }

    fun removeObserver(observer: BackGroundCallback) {
        CALL_BACK_CACHE.remove(observer)
    }

    interface BackGroundCallback {
        /**
         * 应用前后台状态变更回调
         */
        fun onAppStatusChange()
    }

}