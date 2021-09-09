package com.leevinapp.testingdemo.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseUrlModule {
    @Singleton
    @Provides
    fun providerBaseUrl(): String {
        return "https://api.github.com"
    }
}
