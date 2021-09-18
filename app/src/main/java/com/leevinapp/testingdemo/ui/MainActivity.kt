package com.leevinapp.testingdemo.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.leevinapp.testingdemo.R
import com.leevinapp.testingdemo.common.ActivityInjector
import com.leevinapp.testingdemo.common.FragmentInjector
import com.leevinapp.testingdemo.common.ViewModelFactory
import com.leevinapp.testingdemo.di.ActivityComponent
import com.leevinapp.testingdemo.di.ActivityComponentProvider
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ActivityComponentProvider, FragmentInjector {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var fragmentInjector: FragmentInjector

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    private lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent = (application as ActivityInjector).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment)
        supportFragmentManager.commit {
            val fragment = MainFragment.newInstance()
            replace(R.id.container, fragment, fragment::class.java.canonicalName)
        }
        /**
         * viewModelFactory is ActivityComponent graph, @Reusable to make sure the same instance
         * and viewModels {} to have lifecycle to lifecycle owners
         */
        Timber.e("=====viewModelFactory=====$viewModelFactory")
        Timber.e("=====viewModel=====$viewModel")
    }

    override val component: ActivityComponent by lazy {
        // fix ActivityComponent duplicated issue to make sure
        // inject ActivityComponent consistency with inject fragment
        activityComponent
    }

    override fun inject(fragment: Fragment) {
        fragmentInjector.inject(fragment)
    }
}
