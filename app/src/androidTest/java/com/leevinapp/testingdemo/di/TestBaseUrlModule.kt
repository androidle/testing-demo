package com.leevinapp.testingdemo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TestBaseUrlModule {
    @Singleton
    @Provides
    fun providerBaseUrl(): String {
        return "http://localhost:8000"
    }
}
