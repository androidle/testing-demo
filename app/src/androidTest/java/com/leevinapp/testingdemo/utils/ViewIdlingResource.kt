package com.leevinapp.testingdemo.utils

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.ViewFinder
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher

class ViewIdlingResource(
    private val viewMatcher: Matcher<View>,
    private val idleMatcher: Matcher<View>
) : IdlingResource {
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String {
        return this.toString() + viewMatcher.toString()
    }

    override fun isIdleNow(): Boolean {
        val view = getView(viewMatcher)
        val isIdle = idleMatcher.matches(view)
        if (isIdle && resourceCallback != null) {
            resourceCallback?.onTransitionToIdle()
        }
        return isIdle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.resourceCallback = callback
    }

    companion object {
        private fun getView(viewMatcher: Matcher<View>): View {
            try {
                val viewInteraction = onView(viewMatcher)
                val finderField = viewInteraction.javaClass.getDeclaredField("viewFinder")
                finderField.isAccessible = true
                val viewfinder = finderField.get(viewInteraction) as ViewFinder
                return viewfinder.view
            } catch (e: Exception) {
                throw RuntimeException()
            }
        }

        fun waitUntilIdleMatcherIsFulfilled(
            viewMatcher: Matcher<View>,
            idleMatcher: Matcher<View>
        ) {
            val idlingResource = ViewIdlingResource(viewMatcher, idleMatcher)
            try {
                IdlingRegistry.getInstance().register(idlingResource)
                // First call onView is to trigger the idler
                onView(withId(0)).check(doesNotExist())
            } finally {
                IdlingRegistry.getInstance().unregister(idlingResource)
            }
        }
    }
}
