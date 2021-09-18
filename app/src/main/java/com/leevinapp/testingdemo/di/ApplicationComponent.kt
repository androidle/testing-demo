package com.leevinapp.testingdemo.di

import android.content.Context
import com.leevinapp.testingdemo.DemoApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, BaseUrlModule::class])
interface ApplicationComponent {
    // Factory to create instances of the ApplicationComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(application: DemoApplication)

    fun activityComponent(): ActivityComponent.Factory
}
