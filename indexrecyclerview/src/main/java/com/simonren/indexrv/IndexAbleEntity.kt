package com.simonren.indexrv

import javax.sql.StatementEvent

/**
 * @author Simon
 * @date 2020/3/16
 * @time 18:35
 * @desc
 */
interface IndexAbleEntity {
    fun getFieldIndexBy(): String
    fun setFieldIndexBy(indexField: String)
    fun setFieldPinyinIndexBy(pinyin: String)
}