package alone.com.simon.news

import com.simon.basic.core.LogicApplication
import com.simon.basic.core.util.ProcessUtil
import com.simon.news.NewsLogic

/**
 * @author Simon
 * @date 2020/8/28
 * @desc
 */
class NewsApplication : LogicApplication() {

    override fun initializeLogic() {

        registerApplicationLogic(
            ProcessUtil.getProcessName(this, ProcessUtil.getMyProcessId())!!,
            0,
            NewsLogic(this)
        )
    }

    override fun needMultipleProcess(): Boolean {
        return false
    }

}