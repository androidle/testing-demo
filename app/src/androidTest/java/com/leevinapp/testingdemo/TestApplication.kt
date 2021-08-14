package com.leevinapp.testingdemo

import android.app.Application

class TestApplication : Application(), BaseUrlProvider {
    override fun getBaseUrl(): String {
        return "http://localhost:8000"
    }
}