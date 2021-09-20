package com.leevinapp.testingdemo

import android.app.Application
import com.leevinapp.testingdemo.di.ApplicationComponent
import com.leevinapp.testingdemo.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class DemoApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    open val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        DebugUtils.initTimber()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}
