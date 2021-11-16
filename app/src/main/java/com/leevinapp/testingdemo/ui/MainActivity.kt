package com.leevinapp.testingdemo.ui

import android.app.Dialog
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.leevinapp.journeylib.JourneyFeature
import com.leevinapp.journeylib.JourneyActivity
import com.leevinapp.testingdemo.R
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dialog: Dialog

    @Inject
    lateinit var resourcesGlobal: Resources

    @Inject
    lateinit var journeyFeature: JourneyFeature

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment)
        supportFragmentManager.commit {
            val fragment = MainFragment.newInstance()
            replace(R.id.container, fragment, fragment::class.java.canonicalName)
        }

        Timber.e("==========journeyFeature=======$journeyFeature")

        startActivity(Intent(this, JourneyActivity::class.java))
    }
}
