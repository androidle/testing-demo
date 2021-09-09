package com.leevinapp.testingdemo

import com.leevinapp.testingdemo.di.DaggerTestAppComponent
import com.leevinapp.testingdemo.di.TestAppComponent

class TestDemoApplication : DemoApplication() {
    override val appComponent: TestAppComponent = DaggerTestAppComponent.create()
}
