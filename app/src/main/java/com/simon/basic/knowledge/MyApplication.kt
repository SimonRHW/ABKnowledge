package com.simon.basic.knowledge

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}