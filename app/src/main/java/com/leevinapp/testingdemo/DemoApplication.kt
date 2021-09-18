package com.leevinapp.testingdemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DebugUtils.initTimber()
    }
}
