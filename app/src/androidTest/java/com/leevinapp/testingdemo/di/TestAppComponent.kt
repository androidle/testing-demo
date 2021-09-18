package com.leevinapp.testingdemo.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, TestBaseUrlModule::class, ViewModelModule::class])
interface TestAppComponent : ApplicationComponent
