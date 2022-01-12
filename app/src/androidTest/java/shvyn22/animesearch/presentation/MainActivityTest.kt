package shvyn22.animesearch.presentation

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.animesearch.R

@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()

        launchActivity<MainActivity>()
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
}