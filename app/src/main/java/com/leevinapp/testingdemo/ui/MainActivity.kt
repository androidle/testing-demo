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
import javax.inject.Provider

class MainActivity : AppCompatActivity(), ActivityComponentProvider, FragmentInjector {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var activityComponentFactoryProvider: Provider<ActivityComponent.Factory>

    @Inject
    lateinit var fragmentInjector: FragmentInjector

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as ActivityInjector).inject(this)
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
        activityComponentFactoryProvider.get()
            .create(this)
    }

    override fun inject(fragment: Fragment) {
        fragmentInjector.inject(fragment)
    }
}
