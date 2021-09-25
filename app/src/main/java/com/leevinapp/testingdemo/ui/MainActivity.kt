package com.leevinapp.testingdemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.leevinapp.testingdemo.R
import com.leevinapp.testingdemo.common.LoadingPresenter
import com.leevinapp.testingdemo.databinding.ActivitySingleFragmentBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingleFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val loadingPresenter = LoadingPresenter(this)
        binding.loadingButton.setOnClickListener {
            loadingPresenter.showLoading()
        }
    }
}
