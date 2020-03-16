package com.simonren.indexrv.database

interface DataObserver {
    /**
     * this method is called when entire data has load
     * init data
     */
    fun onInit()

    /**
     * this method is called when entire data has change
     * refresh UI
     */
    fun onChange()

    /**
     * this method is called when the entire data becomes invalid
     */
    fun onSetListener(type: Int)
}