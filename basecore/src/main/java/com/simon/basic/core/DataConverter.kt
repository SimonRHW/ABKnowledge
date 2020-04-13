package com.simon.basic.core

/**
 * @author Simon
 * @date 2020/4/13
 * @desc
 */
interface DataConverter<T> {
    fun convert(): T?
}