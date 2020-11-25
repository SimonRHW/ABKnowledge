package com.simon.animation

import android.content.res.Configuration
import com.simon.basic.core.Logic
import com.simon.basic.core.LogicApplication
import com.simon.basic.core.util.ProcessUtil

/**
 * @author Simon
 * @date 2020/11/3
 * @desc
 */
class AnimationApplication : LogicApplication() {

    override fun initializeLogic() {

        registerApplicationLogic(
            ProcessUtil.getProcessName(this, ProcessUtil.getMyProcessId())!!,
            0,
            AnimationLogic(this)
        )
    }

    override fun needMultipleProcess(): Boolean {
        return false
    }

}