package shvyn22.animesearch.ui

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.animesearch.R
import shvyn22.animesearch.data.preferences.PreferencesManager
import shvyn22.animesearch.di.tearDownPreferencesDependencies
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var scope: CoroutineScope

    @Inject
    lateinit var preferences: PreferencesManager

    @Before
    fun init() {
        hiltRule.inject()

        launchActivity<MainActivity>()
    }

    @After
    fun tearDown() {
        tearDownPreferencesDependencies(scope)
    }

    @Test
    fun clickOnNavigateToBookmarks_PressBack_NavigationIsExecuted() {
        onView(withId(R.id.btn_bookmarks))
            .perform(click())

        onView(withId(R.id.tv_bookmarks_hint))
            .check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.btn_bookmarks))
            .check(matches(isDisplayed()))
    }


    @Test
    fun toggleModeIcon_ValidModesAreDisplayed() {
        runTest {
            assertThat(preferences.nightMode.first(), `is`(false))
        }

        onView(withId(R.id.action_mode))
            .perform(click())

        runTest {
            assertThat(preferences.nightMode.first(), `is`(true))
        }

        onView(withId(R.id.action_mode))
            .perform(click())

        runTest {
            assertThat(preferences.nightMode.first(), `is`(false))
        }
    }
}