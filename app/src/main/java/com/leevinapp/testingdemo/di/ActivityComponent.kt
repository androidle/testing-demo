package com.leevinapp.testingdemo.di

import android.app.Activity
import com.leevinapp.testingdemo.ui.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: Activity): ActivityComponent
    }

    fun fragmentComponent(): FragmentComponent.Factory

    fun inject(mainActivity: MainActivity)
}