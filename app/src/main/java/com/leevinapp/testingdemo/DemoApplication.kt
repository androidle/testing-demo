package com.leevinapp.testingdemo

import android.app.Application
import com.leevinapp.testingdemo.di.AppComponent
import com.leevinapp.testingdemo.di.DaggerAppComponent

class DemoApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}
