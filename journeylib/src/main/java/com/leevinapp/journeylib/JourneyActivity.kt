package com.leevinapp.journeylib

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class JourneyActivity : AppCompatActivity() {

    @Inject
    lateinit var journeyFeature: JourneyFeature

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("TAG","==========journeyFeature=======$journeyFeature")
    }
}
