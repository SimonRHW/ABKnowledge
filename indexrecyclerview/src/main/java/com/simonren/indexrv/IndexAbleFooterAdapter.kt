package com.simonren.indexrv

/**
 * @author Simon
 * @date 2020/3/16
 * @time 18:38
 * @desc
 */
abstract class IndexAbleFooterAdapter<T> : AbstractHeaderFooterAdapter<T> {

    constructor(mIndex: String, mIndexTitle: String?, data: List<T>) : super(
        mIndex,
        mIndexTitle,
        data
    ) {
        headerFooterType = EntityWrapper.TYPE_FOOTER
    }


    fun setOnItemFooterClickListener(listener: OnItemFooterClickListener<T>) {
        this.mListener = listener
    }

    fun setOnItemFooterLongClickListener(listener: OnItemFooterLongClickListener<T>) {
        this.mLongListener = listener
    }

    interface OnItemFooterClickListener<T> : OnItemClickListener<T>

    interface OnItemFooterLongClickListener<T> : OnItemLongClickListener<T>

}
