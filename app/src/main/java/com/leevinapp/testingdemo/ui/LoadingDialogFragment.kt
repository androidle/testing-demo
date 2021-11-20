package com.leevinapp.testingdemo.ui

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.leevinapp.testingdemo.R

class LoadingDialogFragment : DialogFragment(R.layout.layout_loading) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FullScreenDialog)
        isCancelable = false
    }

    override fun onResume() {
        super.onResume()
        view?.announceForAccessibility(getString(R.string.loading))
    }
}
