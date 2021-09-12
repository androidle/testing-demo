package com.leevinapp.testingdemo.di

import androidx.fragment.app.Fragment
import com.leevinapp.testingdemo.ui.MainFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance fragment: Fragment): FragmentComponent
    }

    fun inject(mainFragment: MainFragment)
}