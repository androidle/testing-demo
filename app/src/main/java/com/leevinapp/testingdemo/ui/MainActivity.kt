package com.leevinapp.testingdemo.ui

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.leevinapp.testingdemo.R
import com.leevinapp.testingdemo.common.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var dialog: Dialog

    @Inject
    lateinit var resourcesGlobal: Resources

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment)
        supportFragmentManager.commit {
            val fragment = MainFragment.newInstance()
            replace(R.id.container, fragment, fragment::class.java.canonicalName)
        }
    }
}
