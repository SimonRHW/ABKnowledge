package com.simon.basic.core

import android.content.res.Configuration
import com.simon.basic.core.platform.AppStatusManager
import com.simon.basic.core.util.ProcessUtil

/**
 * @author Simon
 * @date 2020/5/10
 * @desc 根据Logic优先级进行初始化的Application基类
 * @see Logic
 * @see PriorityLogicWrapper
 */
abstract class LogicApplication : BaseApplication() {

    /**
     *  mLogicList只持有当前进程的PriorityLogicWrapper对象
     */
    private lateinit var mLogicList: ArrayList<PriorityLogicWrapper>

    /**
     * mLogicClassMap持有所有进程的PriorityLogicWrapper数组对象
     */
    private lateinit var mLogicClassMap: HashMap<String, ArrayList<PriorityLogicWrapper>>

    override fun onCreate() {
        super.onCreate()
        init()
        initializeLogic()
        dispatchLogic()
        instantiateLogic()
    }

    /**
     * 初始化基础服务
     */
    private fun init() {
        mLogicClassMap = HashMap()
        AppStatusManager.instance.init(this)
    }

    /**
     * 由LogicApplication的实现类，去实现这个方法，调用registerApplicationLogic()
     * 注册所有进程的Logic对象
     * @see Logic
     */
    protected abstract fun initializeLogic()

    /**
     * 得到一个属于本进程的ArrayList对象，里面保存着封装类PriorityLogicWrapper
     */
    private fun dispatchLogic() {
        mLogicList = mLogicClassMap[ProcessUtil.getProcessName(this, ProcessUtil.getMyProcessId())]
            ?: ArrayList()
    }

    /**
     * 取得mLogicList中的PriorityLogicWrapper对象，
     * 并按优先级顺序排序Logic对象进行调用
     */
    private fun instantiateLogic() {
        mLogicList.sort()
        if (mLogicList.isNotEmpty()) {
            for (priorityLogicWrapper in mLogicList) {
                priorityLogicWrapper.logicClass.onCreate()
            }
        }
    }

    /**
     * 添加所有来自不同进程的，不同的BaseLogic对象到HashMap中
     * @param processName 进程名
     * @param priority 优先级
     * @param logicClass 继承Logic的对象
     * @return   tempList更新，则mLogicClassMap中的value也跟着更新了，不用再调用mLogicClassMap.put方法
     */
    protected fun registerApplicationLogic(
        processName: String,
        priority: Int,
        logicClass: Logic,
    ): Boolean {
        if (processName.isBlank()) {
            var tempList = mLogicClassMap[processName]
            if (null == tempList) {
                tempList = ArrayList()
                mLogicClassMap[processName] = tempList
            }
            if (tempList.size > 0) {
                for (priorityLogicWrapper in tempList) {
                    if (logicClass == priorityLogicWrapper.logicClass) {
                        throw RuntimeException("$logicClass has registered.")
                    }
                }
            }
            val priorityLogicWrapper = PriorityLogicWrapper(priority, logicClass)
            tempList.add(priorityLogicWrapper)
        }
        return true
    }

    override fun onTerminate() {
        super.onTerminate()
        mLogicList.forEach {
            it.logicClass.onTerminate()
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mLogicList.forEach {
            it.logicClass.onLowMemory()
        }
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        mLogicList.forEach {
            it.logicClass.onTrimMemory(level)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mLogicList.forEach {
            it.logicClass.onConfigurationChanged(newConfig)
        }
    }
}