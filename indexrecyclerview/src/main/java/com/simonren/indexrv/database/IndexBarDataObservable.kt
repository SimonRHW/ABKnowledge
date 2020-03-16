package com.simonren.indexrv.database

import android.database.Observable

/**
 * @author Simon
 * @date 2020/3/16
 * @time 17:02
 * @desc
 */
class IndexBarDataObservable : Observable<IndexBarDataObserver>() {

    fun notifyChange() {
        synchronized(mObservers) {
            mObservers.forEach {
                it.onChange()
            }
        }
    }
}