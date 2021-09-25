package com.leevinapp.testingdemo.common

import android.app.Activity
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.FragmentActivity
import com.leevinapp.testingdemo.common.extensions.hideLoadingDialog
import com.leevinapp.testingdemo.common.extensions.showLoadingDialog
import java.util.concurrent.atomic.AtomicBoolean

class LoadingPresenter(private val activity: Activity) {
    private val handler by lazy { Handler(Looper.getMainLooper()) }
    private val isShow = AtomicBoolean(false)

    fun showLoading() {
        (activity as FragmentActivity).showLoadingDialog()
        isShow.set(true)
    }

    fun hideLoading() {
        if (isShow.compareAndSet(true, false)) {
            handler.postDelayed({
                (activity as FragmentActivity).hideLoadingDialog()
            }, 300)
        }
    }
}
