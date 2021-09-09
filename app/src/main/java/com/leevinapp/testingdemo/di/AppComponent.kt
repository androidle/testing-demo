package com.leevinapp.testingdemo.di

import android.content.Context
import com.leevinapp.testingdemo.ui.MainActivity
import com.leevinapp.testingdemo.ui.MainFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: MainFragment)
    fun inject(activity: MainActivity)
}
