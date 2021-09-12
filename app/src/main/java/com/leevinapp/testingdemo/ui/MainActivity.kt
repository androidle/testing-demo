package com.leevinapp.testingdemo.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.leevinapp.testingdemo.DemoApplication
import com.leevinapp.testingdemo.R
import com.leevinapp.testingdemo.common.ViewModelFactory
import com.leevinapp.testingdemo.di.ActivityComponent
import javax.inject.Inject
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var activityComponent: ActivityComponent

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent = (application as DemoApplication).appComponent
            .activityComponent().create(this)
        activityComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment)
        supportFragmentManager.commit {
            val fragment = MainFragment.newInstance()
            replace(R.id.container, fragment, fragment::class.java.canonicalName)
        }

        Timber.e("=====viewModelFactory=====$viewModelFactory")
        Timber.e("=====viewModel=====$viewModel")
    }
}
