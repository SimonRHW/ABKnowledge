package com.simon.basic.core

import com.simon.basic.core.PriorityLogicWrapper as PriorityLogicWrapperOther

/**
 * @author Simon
 * @date 2020/5/10
 * @desc 包装类，将BaseLogic 按照指定顺序排序（可以按照优先级进行初始化）
 * @see BaseLogic
 */
class PriorityLogicWrapper(
    private var priority: Int,
    var logicClass: Class<out BaseLogic>?
) : Comparable<PriorityLogicWrapperOther> {

    var instance: BaseLogic? = null

    override fun compareTo(other: PriorityLogicWrapperOther): Int {
        return other.priority - priority
    }

}