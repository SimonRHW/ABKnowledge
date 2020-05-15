package com.simon.basic.core

import android.app.Application
import android.content.res.Configuration
import androidx.annotation.NonNull
import com.simon.basic.core.util.ProcessUtil
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * @author Simon
 * @date 2020/5/10
 * @desc
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
                if (priorityLogicWrapper.instance != null) {
                    //找到当前进程的BaseApplicationLogic实例后，执行其onCreate()方法
                    priorityLogicWrapper.instance!!.onCreate()
                }
            }
        }
    }

    private fun init() {
        mLogicClassMap = HashMap()
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
        @NonNull logicClass: Class<out BaseLogic?>
    ): Boolean {
        val result = false
        var tempList = mLogicClassMap[processName]
        if (null == tempList) {
            tempList = ArrayList()
            mLogicClassMap[processName] = tempList
        }
        if (tempList.size > 0) {
            for (priorityLogicWrapper in tempList) {
                if (logicClass.name == priorityLogicWrapper.logicClass!!.name) {
                    throw RuntimeException(logicClass.name + " has registered.")
                }
            }
        }
        val priorityLogicWrapper = PriorityLogicWrapper(priority, logicClass)
        tempList.add(priorityLogicWrapper)
        //tempList更新，则mLogicClassMap中的value也跟着更新了，不用再调用mLogicClassMap.put方法
        return result
    }

    /**
     * 取得mLogicList中的PriorityLogicWrapper对象，并按优先级顺序初始化BaseLogic对象
     */
    private fun instantiateLogic() {
        if (null != mLogicList && mLogicList!!.size > 0) {
            if (null != mLogicList && mLogicList!!.size > 0) {
                mLogicList!!.sort() //根据进程优先级，按顺序初始化
                for (priorityLogicWrapper in mLogicList!!) {
                    try {
                        //调用Class.newInstance()，会创建这个Class的实例，但是不会执行Android中这个类相关的生命周期
                        priorityLogicWrapper.instance =
                            priorityLogicWrapper.logicClass!!.newInstance()
                    } catch (e: InstantiationException) {
                        e.printStackTrace()
                    } catch (e: IllegalAccessException) {
                        e.printStackTrace()
                    }
                    if (null != priorityLogicWrapper.instance) {
                        priorityLogicWrapper.instance!!.setApplication(this)
                    }
                }
            }
        }
    }

    //Application生命周期的处理，下面方法都类似
    override fun onTerminate() {
        super.onTerminate()
        if (null != mLogicList && mLogicList!!.size > 0) {
            for (priorityLogicWrapper in mLogicList!!) {
                if (priorityLogicWrapper.instance != null) {
                    priorityLogicWrapper.instance!!.onTerminate()
                }
            }
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        if (null != mLogicList && mLogicList!!.size > 0) {
            for (priorityLogicWrapper in mLogicList!!) {
                if (priorityLogicWrapper.instance != null) {
                    priorityLogicWrapper.instance!!.onLowMemory()
                }
            }
        }
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (null != mLogicList && mLogicList!!.size > 0) {
            for (priorityLogicWrapper in mLogicList!!) {
                if (priorityLogicWrapper.instance != null) {
                    priorityLogicWrapper.instance!!.onTrimMemory(level)
                }
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (null != mLogicList && mLogicList!!.size > 0) {
            for (priorityLogicWrapper in mLogicList!!) {
                if (priorityLogicWrapper.instance != null) {
                    priorityLogicWrapper.instance!!.onConfigurationChanged(newConfig)
                }
            }
        }
    }
}