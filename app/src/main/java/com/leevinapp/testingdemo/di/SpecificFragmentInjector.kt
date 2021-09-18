package com.leevinapp.testingdemo.di

import androidx.fragment.app.Fragment
import com.leevinapp.testingdemo.common.FragmentInjector
import com.leevinapp.testingdemo.ui.MainFragment

class SpecificFragmentInjector(private val activityComponentProvider: ActivityComponentProvider) :
    FragmentInjector {
    override fun inject(fragment: Fragment) {
        when (fragment) {
            is MainFragment -> fragment.component().inject(fragment)
            else -> throw IllegalArgumentException("Unknown fragment $fragment")
        }
    }

    private fun Fragment.component() =
        activityComponentProvider.component
            .fragmentComponent()
            .create(this)
}
