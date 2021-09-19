package com.leevinapp.testingdemo.di

import android.app.AlertDialog
import android.app.Dialog
import com.leevinapp.testingdemo.di.scope.ActivityScope
import com.leevinapp.testingdemo.ui.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @ActivityScope
    @Provides
    fun provideLoadingSpinnerDialog(activity: MainActivity): Dialog {
        return AlertDialog.Builder(activity).setTitle("Loading").create()
    }
}
