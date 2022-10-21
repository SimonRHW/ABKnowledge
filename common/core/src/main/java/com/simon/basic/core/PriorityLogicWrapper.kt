package com.simon.basic.core

import com.simon.basic.core.PriorityLogicWrapper as PriorityLogicWrapperOther

/**
 * @author Simon
 * @date 2020/5/10
 * @desc 包装类，将Logic 按照指定顺序排序（可以按照优先级进行初始化）
 * @see LogicApplication
 */
class PriorityLogicWrapper(
    private var priority: Int,
    var logicClass: Logic
) : Comparable<PriorityLogicWrapperOther> {

    override fun compareTo(other: PriorityLogicWrapperOther): Int {
        return other.priority - priority
    }

}