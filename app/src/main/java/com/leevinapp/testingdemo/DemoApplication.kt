package com.leevinapp.testingdemo

import android.app.Application

class DemoApplication : Application(), BaseUrlProvider {
    override fun getBaseUrl(): String {
        return "https://api.github.com"
    }
}
