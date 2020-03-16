package com.simonren.indexrv.database

import android.database.Observable
import android.os.Parcel
import android.os.Parcelable

class HeaderFooterDataObservable() : Observable<HeaderFooterDataObserver<Any>>() {

    fun notifyChange() {
        synchronized(mObservers) {
            mObservers.forEach {
                it.onChange()
            }
        }
    }

    fun notifyAdd(header: Boolean, preData: Any, data: Any) {
        synchronized(mObservers) {
            mObservers.forEach {
                it.onAdd(header, preData, data)
            }
        }
    }

    fun notifyRemove(header: Boolean, any: Any) {
        synchronized(mObservers) {
            mObservers.forEach {
                it.onRemove(header, any)
            }
        }
    }

}