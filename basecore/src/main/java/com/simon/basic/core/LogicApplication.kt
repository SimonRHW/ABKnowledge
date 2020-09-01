package com.simon.basic.core

import android.app.Application
import android.content.res.Configuration
import com.simon.basic.core.platform.AppStatusManager
import com.simon.basic.core.util.ProcessUtil
import com.simon.log.LoggerManager

/**
 * @author Simon
 * @date 2020/5/10
 * @desc 根据Logic优先级进行初始化的Application基类
 * @see Logic
 * @see PriorityLogicWrapper
 */
abstract class LogicApplication : Application() {

    //mLogicList只持有当前进程的PriorityLogicWrapper对象
    private var mLogicList: ArrayList<PriorityLogicWrapper>? = null

    //mLogicClassMap持有所有进程的PriorityLogicWrapper数组对象
    private lateinit var mLogicClassMap: HashMap<String, ArrayList<PriorityLogicWrapper>>

    override fun onCreate() {
        super.onCreate()
        init()
        initializeLogic()
        dispatchLogic()
        instantiateLogic()
        if (null != mLogicList && mLogicList!!.size > 0) {
            for (priorityLogicWrapper in mLogicList!!) {
                //找到当前进程的BaseApplicationLogic实例后，执行其onCreate()方法
                priorityLogicWrapper.logicClass.onCreate()
            }
        }
    }

    private fun init() {
        mLogicClassMap = HashMap()
        LoggerManager.getInstance(this)
            .setLogStrategy(LoggerManager.LogStrategy.CONSOLE)
            .setGlobalTag("STAG")
            .showLog(true)
            .setLogLevel(0)
        AppStatusManager.instance.init(this)
    }

    /**
     * 由LogicApplication的实现类，去实现这个方法，调用registerApplicationLogic()
     * 注册所有进程的BaseApplicationLogic对象
     */
    protected abstract fun initializeLogic()

    /**
     * 得到一个属于本进程的ArrayList对象，里面保存着封装类PriorityLogicWrapper
     */
    private fun dispatchLogic() {
        mLogicList = mLogicClassMap[ProcessUtil.getProcessName(this, ProcessUtil.getMyProcessId())]
    }

    abstract fun needMultipleProcess(): Boolean

    /**
     * 添加所有来自不同进程的，不同的BaseLogic对象到HashMap中
     * @param processName 进程名
     * @param priority 优先级
     * @param logicClass 继承BaseLogic的对象
     * @return
     */
    protected fun registerApplicationLogic(
        processName: String,
        priority: Int,
        logicClass: Logic
    ): Boolean {
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
        //tempList更新，则mLogicClassMap中的value也跟着更新了，不用再调用mLogicClassMap.put方法
        return true
    }

    /**
     * 取得mLogicList中的PriorityLogicWrapper对象，并按优先级顺序排序Logic对象进行初始化
     */
    private fun instantiateLogic() {
        if (null != mLogicList && mLogicList!!.size > 0) {
            if (null != mLogicList && mLogicList!!.size > 0) {
                mLogicList!!.sort() //根据进程优先级，按顺序初始化
            }
        }
    }

    //Application生命周期的处理，下面方法都类似
    override fun onTerminate() {
        super.onTerminate()
        if (null != mLogicList && mLogicList!!.size > 0) {
            for (priorityLogicWrapper in mLogicList!!) {
                priorityLogicWrapper.logicClass.onTerminate()
            }
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        if (null != mLogicList && mLogicList!!.size > 0) {
            for (priorityLogicWrapper in mLogicList!!) {
                priorityLogicWrapper.logicClass.onLowMemory()
            }
        }
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (null != mLogicList && mLogicList!!.size > 0) {
            for (priorityLogicWrapper in mLogicList!!) {
                priorityLogicWrapper.logicClass.onTrimMemory(level)
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (null != mLogicList && mLogicList!!.size > 0) {
            for (priorityLogicWrapper in mLogicList!!) {
                priorityLogicWrapper.logicClass.onConfigurationChanged(newConfig)
            }
        }
    }
}