package com.leevinapp.testingdemo

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner
import com.facebook.testing.screenshot.ScreenshotRunner

class ScreenshotTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, TestDemoApplication::class.java.name, context)
    }

    override fun onCreate(arguments: Bundle) {
        ScreenshotRunner.onCreate(this, arguments)
        super.onCreate(arguments)
    }

    override fun finish(resultCode: Int, results: Bundle) {
        ScreenshotRunner.onDestroy()
        super.finish(resultCode, results)
    }
}
