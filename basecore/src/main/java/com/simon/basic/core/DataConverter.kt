package com.simon.basic.core

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