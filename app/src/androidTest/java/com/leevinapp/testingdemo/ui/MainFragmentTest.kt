package com.leevinapp.testingdemo.ui

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.facebook.testing.screenshot.Screenshot
import com.leevinapp.testingdemo.R
import com.leevinapp.testingdemo.utils.ResourceFile
import com.leevinapp.testingdemo.utils.ViewIdlingResource
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class MainFragmentTest {

    private val mockWebServer = MockWebServer()
    private var activity: FragmentActivity? = null

    @Before
    fun setup() {
        mockWebServer.start(8000)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testSuccessfulResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(loadLocalResponse("success_response.json"))
            }
        }

        launchAndMoveToResume()
        Thread.sleep(1_000)
        ViewIdlingResource.waitUntilIdleMatcherIsFulfilled(withId(R.id.textviewSuccess), isDisplayed())

        Espresso.onView(withId(R.id.progress_bar))
            .check(matches(not(isDisplayed())))
        Espresso.onView(withId(R.id.textviewSuccess))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.textview))
            .check(matches(not(isDisplayed())))

        Screenshot.snapActivity(activity)
            .setName("Success_Page")
            .record()
    }

    @Test
    fun testLoadingBeforeResponseReturn() {
        launchAndMoveToResume()

        Espresso.onView(withId(R.id.progress_bar))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.textviewSuccess))
            .check(matches(not(isDisplayed())))
        Espresso.onView(withId(R.id.textview))
            .check(matches(not(isDisplayed())))
        Espresso.onView(withId(R.id.textview))
            .check(matches(not(isDisplayed())))

        Screenshot.snapActivity(activity)
            .setName("Loading_View")
            .record()
    }

    @Test
    fun testFailedResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().throttleBody(1024, 5, TimeUnit.SECONDS)
            }
        }

        launchAndMoveToResume()
        Thread.sleep(1_000)
        ViewIdlingResource.waitUntilIdleMatcherIsFulfilled(allOf(withId(R.id.textview), withText(R.string.error_message)), isDisplayed())

        Espresso.onView(withId(R.id.progress_bar))
            .check(matches(not(isDisplayed())))
        Espresso.onView(withId(R.id.textviewSuccess))
            .check(matches(not(isDisplayed())))
        Espresso.onView(withId(R.id.textview))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.textview))
            .check(matches(withText(R.string.error_message)))

        Screenshot.snapActivity(activity)
            .setName("Failed_Page")
            .record()
    }

    @Test
    fun testEmptyResponse() {
        // Given
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody("[]")
            }
        }
        // When
        launchAndMoveToResume()
        Thread.sleep(1_000)
        ViewIdlingResource.waitUntilIdleMatcherIsFulfilled(allOf(withId(R.id.textview), withText(R.string.no_data_message)), isCompletelyDisplayed())

        // Then
        Espresso.onView(withId(R.id.progress_bar))
            .check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        Espresso.onView(withId(R.id.textviewSuccess))
            .check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        Espresso.onView(withId(R.id.textview))
            .check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(withId(R.id.textview))
            .check(matches(withText(R.string.no_data_message)))

        Screenshot.snapActivity(activity)
            .setName("Empty_Page")
            .record()
    }

    fun loadLocalResponse(path: String): String {
        return ResourceFile(path).readText()
    }

    private fun launchAndMoveToResume() {
        launchFragmentInContainer<MainFragment>(themeResId = R.style.AppTheme).moveToState(
            Lifecycle.State.RESUMED
        ).onFragment {
            this.activity = it.activity
        }
    }
}
