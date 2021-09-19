package com.leevinapp.testingdemo.di

import com.leevinapp.testingdemo.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindActivityModule {
    @ContributesAndroidInjector(modules = [BindFragmentModule::class, ViewModelModule::class])
    abstract fun bindMainActivity(): MainActivity
}
