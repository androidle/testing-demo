package com.leevinapp.testingdemo.di

import com.leevinapp.testingdemo.ui.MainFragment
import com.leevinapp.testingdemo.ui.OtherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindFragmentModule {

    @ContributesAndroidInjector
    abstract fun bindMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun bindOtherFragment(): OtherFragment
}
