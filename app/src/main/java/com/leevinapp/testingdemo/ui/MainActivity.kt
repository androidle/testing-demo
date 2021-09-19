package com.leevinapp.testingdemo.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.leevinapp.testingdemo.R
import com.leevinapp.testingdemo.common.ViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
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

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}
