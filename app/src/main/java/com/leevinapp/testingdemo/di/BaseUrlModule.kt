package com.leevinapp.testingdemo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BaseUrlModule {
    @Singleton
    @Provides
    fun providerBaseUrl(): String {
        return "https://api.github.com"
    }
}
