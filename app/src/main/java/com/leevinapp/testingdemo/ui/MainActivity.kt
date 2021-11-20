package com.leevinapp.testingdemo.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import com.leevinapp.testingdemo.R
import com.leevinapp.testingdemo.common.LoadingPresenter
import com.leevinapp.testingdemo.databinding.ActivitySingleFragmentBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingleFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(MainActivity.theme)
        binding = ActivitySingleFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val loadingPresenter = LoadingPresenter(this)
        binding.loadingButton.setOnClickListener {
            loadingPresenter.showLoading()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_theme, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val theme = when (item.itemId) {
            R.id.themeDefault -> {
                R.style.AppTheme
            }
            R.id.theme1 -> {
                R.style.AppTheme_Gray
            }
            R.id.theme2 -> {
                R.style.AppTheme_Crane
            }
            R.id.theme3 -> {
                R.style.AppTheme_OwlPink
            }
            else -> 0
        }
        switchTheme(theme)
        return true
    }

    private fun switchTheme(@StyleRes theme: Int) {
        MainActivity.theme = theme
        recreate()
    }

    companion object {
        var theme: Int = 0
    }
}
