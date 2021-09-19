package com.leevinapp.testingdemo.di

import com.leevinapp.testingdemo.di.scope.ActivityScope
import com.leevinapp.testingdemo.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            BindFragmentModule::class,
            ViewModelModule::class,
            MainActivityModule::class
        ]
    )
    abstract fun bindMainActivity(): MainActivity
}
