package com.leevinapp.testingdemo.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestBaseUrlModule {
    @Singleton
    @Provides
    fun providerBaseUrl(): String {
        return "http://localhost:8000"
    }
}
