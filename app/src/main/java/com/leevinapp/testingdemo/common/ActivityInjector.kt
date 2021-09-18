package com.leevinapp.testingdemo.common

import android.app.Activity
import com.leevinapp.testingdemo.di.ActivityComponent

interface ActivityInjector {
    fun inject(activity: Activity): ActivityComponent
}
