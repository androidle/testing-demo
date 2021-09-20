package com.leevinapp.testingdemo.di

import android.app.AlertDialog
import android.app.Dialog
import com.leevinapp.testingdemo.TestActivity
import com.leevinapp.testingdemo.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class TestActivityModule {

    @ActivityScope
    @Provides
    fun provideLoadingSpinnerDialog(activity: TestActivity): Dialog {
        return AlertDialog.Builder(activity).setTitle("Loading").create()
    }
}
