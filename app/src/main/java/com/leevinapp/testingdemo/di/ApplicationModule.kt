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
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val REQUEST_TIMEOUT = 30L

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun providerOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .addLoggingInterceptor()
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
    fun providerResources(context: Context): Resources = context.resources
}
