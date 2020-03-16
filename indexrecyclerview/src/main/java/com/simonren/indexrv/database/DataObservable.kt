package com.simonren.indexrv.database

import android.database.Observable

class DataObservable : Observable<DataObserver>() {

    /**
     * Invokes {@link DataObserver#onInit()}  on each observer.
     * Called when the data set is no longer valid and cannot be queried again,
     * such as when the data set has been closed.
     */
    fun notifyInit() {
        synchronized(mObservers) {
            mObservers.forEach {
                it.onInit()
            }
        }
    }

    /**
     * Invokes {@link DataObserver#onChange} on each observer.
     * Called when the contents of the data set have changed.  The recipient
     * will obtain the new contents the next time it queries the data set.
     */
    fun notifyChange() {
        synchronized(mObservers) {
            mObservers.forEach {
                it.onChange()
            }
        }
    }


    /**
     * Invokes {@link DataObserver#onSetListener(int)} on each observer.
     * Called when the data set is no longer valid and cannot be queried again,
     * such as when the data set has been closed.
     */
    fun notifySetListener(type: Int) {
        synchronized(mObservers) {
            mObservers.forEach {
                it.onSetListener(type)
            }
        }
    }

}