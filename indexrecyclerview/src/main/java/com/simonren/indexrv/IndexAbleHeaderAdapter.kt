package com.simonren.indexrv

/**
 * @author Simon
 * @date 2020/3/16
 * @time 18:38
 * @desc
 */
abstract class IndexAbleHeaderAdapter<T> : AbstractHeaderFooterAdapter<T> {

    constructor(mIndex: String, mIndexTitle: String?, data: List<T>) : super(
        mIndex,
        mIndexTitle,
        data
    ) {
        headerFooterType = EntityWrapper.TYPE_HEADER
    }


    fun setOnItemFooterClickListener(listener: OnItemHeaderClickListener<T>) {
        this.mListener = listener
    }

    fun setOnItemFooterLongClickListener(listener: OnItemHeaderLongClickListener<T>) {
        this.mLongListener = listener
    }

    interface OnItemHeaderClickListener<T> : OnItemClickListener<T>

    interface OnItemHeaderLongClickListener<T> : OnItemLongClickListener<T>

}
