package com.leevinapp.testingdemo

import timber.log.Timber

object DebugUtils {
    fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
}
