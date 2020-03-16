package com.simonren.indexrv

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simonren.indexrv.database.HeaderFooterDataObservable
import com.simonren.indexrv.database.HeaderFooterDataObserver
import com.simonren.indexrv.database.IndexBarDataObservable
import com.simonren.indexrv.database.IndexBarDataObserver
import java.util.*

/**
 * @author Simon
 * @date 2020/3/16
 * @time 17:36
 * @desc
 */
abstract class AbstractHeaderFooterAdapter<T>(
    private val mIndex: String, private val mIndexTitle: String?, data: List<T>
) {

    private val mDataSetObservable = HeaderFooterDataObservable()
    private val mIndexDataSetObservable = IndexBarDataObservable()

    var mEntityWrapperList = ArrayList<EntityWrapper<T>>()
    protected var mListener: OnItemClickListener<T>? = null
    protected var mLongListener: OnItemLongClickListener<T>? = null
    var headerFooterType: Int

    init {
        headerFooterType = EntityWrapper.TYPE_HEADER
        if (mIndexTitle != null) {
            val wrapper = wrapEntity()
            wrapper.itemType = EntityWrapper.TYPE_TITLE
        }
        for (i in data.indices) {
            val wrapper = wrapEntity()
            wrapper.data = data[i]
        }
    }

    private fun wrapEntity(): EntityWrapper<T> {
        val wrapper = EntityWrapper<T>()
        wrapper.index = mIndex
        wrapper.indexTitle = mIndexTitle
        wrapper.headerFooterType = headerFooterType
        mEntityWrapperList.add(wrapper)
        return wrapper
    }

    private fun wrapEntity(pos: Int): EntityWrapper<T> {
        val wrapper = EntityWrapper<T>()
        wrapper.index = mIndex
        wrapper.indexTitle = mIndexTitle
        wrapper.headerFooterType = headerFooterType
        mEntityWrapperList.add(pos, wrapper)
        return wrapper
    }

    abstract val itemViewType: Int
    abstract fun onCreateContentViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder?
    abstract fun onBindContentViewHolder(holder: RecyclerView.ViewHolder?, entity: T)

    fun notifyDataSetChanged() {
        mDataSetObservable.notifyChange()
    }

    fun addData(data: T) {
        val size = mEntityWrapperList.size
        val wrapper = wrapEntity()
        wrapper.itemType = itemViewType
        wrapper.data = data
        if (size > 0) {
            mDataSetObservable.notifyAdd(
                headerFooterType == EntityWrapper.TYPE_HEADER,
                mEntityWrapperList[size - 1],
                wrapper
            )
            mIndexDataSetObservable.notifyChange()
        }
    }

    fun addData(position: Int, data: T) {
        val size = mEntityWrapperList.size
        if (position >= size) {
            return
        }
        val wrapper = wrapEntity(position + 1)
        wrapper.itemType = itemViewType
        wrapper.data = data
        if (size > 0) {
            mDataSetObservable.notifyAdd(
                headerFooterType == EntityWrapper.TYPE_HEADER,
                mEntityWrapperList[position],
                wrapper
            )
            mIndexDataSetObservable.notifyChange()
        }
    }

    fun addDataSet(data: List<T>) {
        for (i in data.indices) {
            addData(data[i])
        }
    }

    fun removeData(data: T) {
        for (wrapper in mEntityWrapperList) {
            if (wrapper.data === data) {
                mEntityWrapperList.remove(wrapper)
                mDataSetObservable.notifyRemove(
                    headerFooterType == EntityWrapper.TYPE_HEADER,
                    wrapper
                )
                mIndexDataSetObservable.notifyChange()
                return
            }
        }
    }

    val dataSet: ArrayList<EntityWrapper<T>>
        get() {
            for (wrapper in mEntityWrapperList) {
                if (wrapper.itemType == EntityWrapper.TYPE_CONTENT) {
                    wrapper.itemType = itemViewType
                }
            }
            return mEntityWrapperList
        }

    fun registerDataSetObserver(observer: HeaderFooterDataObserver<Any>) {
        mDataSetObservable.registerObserver(observer)
    }

    fun unregisterDataSetObserver(observer: HeaderFooterDataObserver<Any>) {
        mDataSetObservable.unregisterObserver(observer)
    }

    fun registerIndexBarDataSetObserver(observer: IndexBarDataObserver) {
        mIndexDataSetObservable.registerObserver(observer)
    }

    fun unregisterIndexBarDataSetObserver(observer: IndexBarDataObserver) {
        mIndexDataSetObservable.unregisterObserver(observer)
    }


    interface OnItemClickListener<T> {
        fun onItemClick(view: View, currentPosition: Int, entity: T)
    }

    interface OnItemLongClickListener<T> {
        fun onItemLongClick(view: View, currentPosition: Int, entity: T)
    }


}