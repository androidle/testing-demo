package com.leevinapp.testingdemo.di

import com.leevinapp.testingdemo.ui.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindFragmentModule {
    @ContributesAndroidInjector
    abstract fun bindMainFragment(): MainFragment
}
