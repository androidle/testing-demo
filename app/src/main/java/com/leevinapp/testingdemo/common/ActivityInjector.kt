package com.leevinapp.testingdemo.common

import android.app.Activity

interface ActivityInjector {
    fun inject(activity: Activity)
}
