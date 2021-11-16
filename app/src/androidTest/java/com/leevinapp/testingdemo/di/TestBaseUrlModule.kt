package com.leevinapp.testingdemo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [BaseUrlModule::class]
)
class TestBaseUrlModule {
    @Singleton
    @Provides
    fun providerBaseUrl(): String {
        return "https://localhost:8000"
    }
}
