package com.leevinapp.testingdemo.di

import android.app.Activity
import com.leevinapp.testingdemo.common.FragmentInjector
import dagger.Module
import dagger.Provides

@Module(includes = [ViewModelModule::class], subcomponents = [FragmentComponent::class])
class ActivityModule {

    @Provides
    fun providerActivityComponentProvider(activity: Activity) = activity as ActivityComponentProvider

    @Provides
    fun providerFragmentInjector(activityComponentProvider: ActivityComponentProvider): FragmentInjector =
        SpecificFragmentInjector(activityComponentProvider)
}
