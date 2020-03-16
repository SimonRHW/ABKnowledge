package com.simonren.indexrv.database

/**
 * @author Simon
 * @date 2020/3/16
 * @time 16:34
 * @desc
 */
interface HeaderFooterDataObserver<T> {

    fun onChange()

    fun onAdd(header: Boolean, preData: T, data: T)

    fun onRemove(header: Boolean, any: Any)
}