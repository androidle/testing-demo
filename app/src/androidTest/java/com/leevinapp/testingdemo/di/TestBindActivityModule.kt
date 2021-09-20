package com.leevinapp.testingdemo.di

import com.leevinapp.testingdemo.di.scope.ActivityScope
import com.leevinapp.testingdemo.ui.TestActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TestBindActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            ViewModelModule::class,
            TestActivityModule::class,
            BindFragmentModule::class
        ]
    )
    abstract fun bindTestActivity(): TestActivity
}
