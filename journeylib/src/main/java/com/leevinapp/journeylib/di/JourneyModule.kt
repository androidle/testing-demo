package com.leevinapp.journeylib.di

import com.leevinapp.journeylib.JourneyFeature
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class JourneyModule {

    @Singleton
    @Provides
    fun providerFeature(): JourneyFeature {
        return JourneyFeature()
    }
}