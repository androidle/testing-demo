package com.leevinapp.testingdemo.di

import android.app.Activity
import com.leevinapp.testingdemo.common.ActivityInjector
import com.leevinapp.testingdemo.ui.MainActivity

class SpecificActivityInjector(private val applicationComponentProvider: ApplicationComponentProvider) :
    ActivityInjector {
    override fun inject(activity: Activity) = when (activity) {
        is MainActivity -> activity.component().inject(activity)
        else -> throw IllegalArgumentException("Unknown activity $activity")
    }

    private fun Activity.component() =
        applicationComponentProvider.component
            .activityComponent()
            .create(this)
}
