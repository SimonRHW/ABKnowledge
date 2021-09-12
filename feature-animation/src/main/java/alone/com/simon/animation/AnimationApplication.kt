package com.simon.animation

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

}