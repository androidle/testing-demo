package com.leevinapp.testingdemo

import okhttp3.OkHttpClient

fun OkHttpClient.Builder.addLoggingInterceptor(): OkHttpClient.Builder {
    return this
}
