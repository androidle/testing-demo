package com.leevinapp.testingdemo.ui

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.testing.launchFragment
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.facebook.testing.screenshot.Screenshot
import com.google.common.truth.Truth.assertThat
import com.leevinapp.testingdemo.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoadingDialogFragmentTest {

    lateinit var dialogFragment: DialogFragment

    @Test
    fun launchDialogFragmentAndVerifyUI() {
        // Use launchFragment to launch the dialog fragment in a dialog.
        val scenario = launchFragment<LoadingDialogFragment>()

        scenario.onFragment { fragment ->
            dialogFragment = fragment
            assertThat(fragment.dialog).isNotNull()
            assertThat(fragment.requireDialog().isShowing).isTrue()
        }

        // Now use espresso to look for the fragment's text view and verify it is displayed.
        Espresso.onView(ViewMatchers.withId(R.id.loadingProgressBar)).inRoot(RootMatchers.isDialog())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Screenshot.snap(dialogFragment.dialog?.window?.decorView)
            .setName("Loading_Dialog_View")
            .record()
    }
}
