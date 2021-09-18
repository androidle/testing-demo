package com.leevinapp.testingdemo.di

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @ActivityScoped
    @Provides
    fun providerLoadingSpinner(@ActivityContext context: Context): Dialog {
        return AlertDialog.Builder(context).setTitle("loading Test")
            .create()
    }
}
