package com.leevinapp.testingdemo.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestAppModule::class, ViewModelModule::class])
interface TestAppComponent : AppComponent
