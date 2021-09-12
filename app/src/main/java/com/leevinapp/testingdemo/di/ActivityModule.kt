package com.leevinapp.testingdemo.di

import dagger.Module

@Module(includes = [ViewModelModule::class], subcomponents = [FragmentComponent::class])
class ActivityModule