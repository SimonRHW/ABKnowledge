package com.simonren.indexrv

/**
 * @author Simon
 * @date 2020/3/16
 * @time 17:05
 * @desc
 */
class EntityWrapper<T> {
    var index: String? = null
    var indexTitle: String? = null
    var pinyin: String? = null
    var indexByField: String? = null
    var data: T? = null

    var originalPosition = -1
    var itemType = TYPE_CONTENT
    var headerFooterType = 0

    internal constructor() {}
    internal constructor(index: String?, itemType: Int) {
        this.index = index
        indexTitle = index
        pinyin = index
        this.itemType = itemType
    }


    val isTitle: Boolean
        get() = itemType == TYPE_TITLE

    val isContent: Boolean
        get() = itemType == TYPE_CONTENT

    val isHeader: Boolean
        get() = headerFooterType == TYPE_HEADER

    val isFooter: Boolean
        get() = headerFooterType == TYPE_FOOTER

    companion object {
        const val TYPE_TITLE = Int.MAX_VALUE - 1
        const val TYPE_CONTENT = Int.MAX_VALUE
        const val TYPE_HEADER = 1
        const val TYPE_FOOTER = 2
    }

}