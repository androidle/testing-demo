package com.leevinapp.testingdemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.leevinapp.testingdemo.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment)
        supportFragmentManager.commit {
            val fragment = MainFragment.newInstance()
            replace(R.id.container, fragment, fragment::class.java.canonicalName)
        }
    }
}
