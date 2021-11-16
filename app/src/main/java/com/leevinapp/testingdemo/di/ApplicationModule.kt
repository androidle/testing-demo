package com.leevinapp.testingdemo.di

import android.content.Context
import android.content.res.Resources
import com.google.gson.GsonBuilder
import com.leevinapp.testingdemo.addLoggingInterceptor
import com.leevinapp.testingdemo.data.GithubApiService
import com.leevinapp.testingdemo.data.UserDataRepositoryImpl
import com.leevinapp.testingdemo.repository.UserDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val REQUEST_TIMEOUT = 30L

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun providerOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .addLoggingInterceptor()
            .connectionSpecs(listOf(ConnectionSpec.MODERN_TLS))
            .build()
    }

    @Singleton
    @Provides
    fun providerRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setPrettyPrinting().create()
                )
            )
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providerGithubApiService(retrofit: Retrofit): GithubApiService {
        return retrofit.create(GithubApiService::class.java)
    }

    @Singleton
    @Provides
    fun providerUserDataRepository(githubApiService: GithubApiService): UserDataRepository {
        return UserDataRepositoryImpl(githubApiService)
    }

    @Provides
    fun providerResources(@ApplicationContext context: Context): Resources = context.resources
}
