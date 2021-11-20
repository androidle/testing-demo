package com.leevinapp.testingdemo.common.extensions

import androidx.fragment.app.FragmentActivity
import com.leevinapp.testingdemo.ui.LoadingDialogFragment

fun FragmentActivity.showLoadingDialog() {
    supportFragmentManager
        .beginTransaction()
        .add(LoadingDialogFragment(), LoadingDialogFragment::class.java.canonicalName)
        .commit()
}

fun FragmentActivity.hideLoadingDialog() {
    supportFragmentManager
        .findFragmentByTag(LoadingDialogFragment::class.java.canonicalName)?.let {
            supportFragmentManager.beginTransaction().remove(it).commit()
        }
}
