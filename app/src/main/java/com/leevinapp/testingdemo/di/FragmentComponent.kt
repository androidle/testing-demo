package com.leevinapp.testingdemo.di

import androidx.fragment.app.Fragment
import com.leevinapp.testingdemo.di.scope.FragmentScope
import com.leevinapp.testingdemo.ui.MainFragment
import dagger.BindsInstance
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(mainFragment: MainFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance fragment: Fragment): FragmentComponent
    }
}
