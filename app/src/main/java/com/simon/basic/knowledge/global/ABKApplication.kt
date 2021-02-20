package com.simon.basic.knowledge.global

import com.alibaba.android.arouter.launcher.ARouter
import com.simon.basic.core.LogicApplication
import com.simon.basic.core.util.ProcessUtil
import com.simon.news.NewsLogic
import com.simon.animation.AnimationLogic
import io.realm.Realm
import io.realm.RealmConfiguration

class ABKApplication : LogicApplication() {

    override fun initializeLogic() {

        /* 调试模式不是必须开启，但是为了防止有用户开启了InstantRun，但是忘了开调试模式，导致无法使用Demo，
        如果使用了InstantRun，必须在 初始化之前开启调试模式，但是上线前需要关闭，
        InstantRun仅用于开发阶段，线上开启调试模式有安全风险，可以使用BuildConfig.DEBUG来区分环境 */
        ARouter.openDebug()
        ARouter.init(this)
        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
            .name("Abk.realm")
            .build()
        Realm.setDefaultConfiguration(realmConfig)

        registerApplicationLogic(
            ProcessUtil.getProcessName(this, ProcessUtil.getMyProcessId())!!,
            10,
            ABKLogic(this)
        )
        registerApplicationLogic(
            ProcessUtil.getProcessName(this, ProcessUtil.getMyProcessId())!!,
            0,
            NewsLogic(this)
        )

        registerApplicationLogic(
            ProcessUtil.getProcessName(this, ProcessUtil.getMyProcessId())!!,
            0,
            AnimationLogic(this)
        )
    }

    override fun needMultipleProcess(): Boolean {
        return false
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}