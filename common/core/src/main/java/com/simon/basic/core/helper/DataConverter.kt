package com.simon.basic.core.helper

/**
 * @author Simon
 * @date 2020/4/13
 * @desc
 */
interface DataConverter<T> {
    /**
     * Gets data.
     *
     * @return 业务数据模型 data
     */
    fun convert(): T?
}