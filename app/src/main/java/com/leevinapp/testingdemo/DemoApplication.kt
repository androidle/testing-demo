package com.leevinapp.testingdemo

import android.app.Activity
import android.app.Application
import com.leevinapp.testingdemo.common.ActivityInjector
import com.leevinapp.testingdemo.di.ActivityComponent
import com.leevinapp.testingdemo.di.ApplicationComponent
import com.leevinapp.testingdemo.di.ApplicationComponentProvider
import com.leevinapp.testingdemo.di.DaggerApplicationComponent
import javax.inject.Inject

open class DemoApplication : Application(), ApplicationComponentProvider, ActivityInjector {

    override val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    @Inject
    lateinit var activityInjector: ActivityInjector

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        DebugUtils.initTimber()
    }

    override fun inject(activity: Activity): ActivityComponent {
        return activityInjector.inject(activity)
    }
}
