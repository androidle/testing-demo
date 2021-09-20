package com.leevinapp.testingdemo.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.facebook.testing.screenshot.Screenshot
import com.leevinapp.testingdemo.R
import com.leevinapp.testingdemo.utils.ResourceFile
import com.leevinapp.testingdemo.utils.withIndex
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class OtherFragmentTest {

    private val mockWebServer = MockWebServer()

    private lateinit var activity: FragmentActivity

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

        ActivityScenario.launch(MainActivity::class.java).onActivity {
            this.activity = it
        }
        // TODO: 2021/9/20 how to decouple the otherFragment test 
        Espresso.onView(withId(R.id.buttonOther)).perform(click())

//        ViewIdlingResource.waitUntilIdleMatcherIsFulfilled(withIndex(withId(R.id.textviewSuccess),1), isDisplayed())

        Espresso.onView(withIndex(withId(R.id.progress_bar), 1))
            .check(matches(not(isDisplayed())))
        Espresso.onView(withIndex(withId(R.id.textviewSuccess), 1))
            .check(matches(isDisplayed()))
        Espresso.onView(withIndex(withId(R.id.textview), 1))
            .check(matches(not(isDisplayed())))

        Screenshot.snapActivity(activity)
            .setName("Other_Success_Page")
            .record()
    }

    @Test
    fun testLoadingBeforeResponseReturn() {
        ActivityScenario.launch(MainActivity::class.java).onActivity {
            this.activity = it
        }

        Espresso.onView(withId(R.id.buttonOther)).perform(click())

        Espresso.onView(withIndex(withId(R.id.progress_bar), 1))
            .check(matches(isDisplayed()))
        Espresso.onView(withIndex(withId(R.id.textviewSuccess), 1))
            .check(matches(not(isDisplayed())))
        Espresso.onView(withIndex(withId(R.id.textview), 1))
            .check(matches(not(isDisplayed())))
        Espresso.onView(withIndex(withId(R.id.textview), 1))
            .check(matches(not(isDisplayed())))

        Screenshot.snapActivity(activity)
            .setName("Other_Loading_View")
            .record()
    }

    @Test
    fun testFailedResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().throttleBody(1024, 5, TimeUnit.SECONDS)
            }
        }

        ActivityScenario.launch(MainActivity::class.java).onActivity {
            this.activity = it
        }

        Espresso.onView(withId(R.id.buttonOther)).perform(click())
//        ViewIdlingResource.waitUntilIdleMatcherIsFulfilled(allOf(withId(R.id.textview), withText(R.string.error_message)), isDisplayed())

        Espresso.onView(withIndex(withId(R.id.progress_bar), 1))
            .check(matches(not(isDisplayed())))
        Espresso.onView(withIndex(withId(R.id.textviewSuccess), 1))
            .check(matches(not(isDisplayed())))
        Espresso.onView(withIndex(withId(R.id.textview), 1))
            .check(matches(isDisplayed()))
        Espresso.onView(withIndex(withId(R.id.textview), 1))
            .check(matches(withText(R.string.error_message)))

        Screenshot.snapActivity(activity)
            .setName("Other_Failed_Page")
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
        ActivityScenario.launch(MainActivity::class.java).onActivity {
            this.activity = it
        }

        Espresso.onView(withId(R.id.buttonOther)).perform(click())
//        ViewIdlingResource.waitUntilIdleMatcherIsFulfilled(allOf(withId(R.id.textview), withText(R.string.no_data_message)), isCompletelyDisplayed())

        // Then
        Espresso.onView(withIndex(withId(R.id.progress_bar), 1))
            .check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        Espresso.onView(withIndex(withId(R.id.textviewSuccess), 1))
            .check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        Espresso.onView(withIndex(withId(R.id.textview), 1))
            .check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(withIndex(withId(R.id.textview), 1))
            .check(matches(withText(R.string.no_data_message)))

        Screenshot.snapActivity(activity)
            .setName("Other_Empty_Page")
            .record()
    }

    fun loadLocalResponse(path: String): String {
        return ResourceFile(path).readText()
    }

    fun FragmentActivity.replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, fragment::class.java.canonicalName)

        supportFragmentManager.executePendingTransactions()
    }

    fun FragmentActivity.addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment, fragment::class.java.canonicalName)
        // immediately executing any such pending operations,
        supportFragmentManager.executePendingTransactions()
    }
}
