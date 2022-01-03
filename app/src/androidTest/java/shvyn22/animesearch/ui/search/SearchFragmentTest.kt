package shvyn22.animesearch.ui.search

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.animesearch.R
import shvyn22.animesearch.api.FakeApiInterface
import shvyn22.animesearch.util.animeInfo1
import shvyn22.animesearch.util.animeInfo2
import shvyn22.animesearch.util.launchFragmentInHiltContainer
import shvyn22.animesearch.util.withItemCount
import javax.inject.Inject

@HiltAndroidTest
class SearchFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var api: FakeApiInterface

    @Before
    fun init() {
        hiltRule.inject()

        launchFragmentInHiltContainer<SearchFragment>() {
            // this.getImageFromFile()
        }
    }

    @Test
    fun remoteIsAvailable_populateApiWith2Items_2itemsAreInView() {
        api.initResponse(listOf(animeInfo1, animeInfo2))

        onView(withId(R.id.btn_search))
            .perform(click())

        onView(withId(R.id.rv_result))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rv_result))
            .check(withItemCount(2))

        onView(withText(animeInfo1.anilistInfo.title.toString()))
            .check(matches(isDisplayed()))

        onView(withText(animeInfo2.anilistInfo.title.toString()))
            .check(matches(isDisplayed()))
    }

    @Test
    fun remoteIsAvailable_populateApiWithNoItems_ErrorIsThrown() {
        onView(withId(R.id.btn_search))
            .perform(click())

        onView(withId(R.id.rv_result))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rv_result))
            .check(withItemCount(0))

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(
                matches(
                    withText(
                        ApplicationProvider.getApplicationContext<Context>().getString(
                            R.string.text_error_fetching, "null"
                        )
                    )
                )
            )
    }

    @Test
    fun remoteIsNotAvailable_ErrorIsThrown() {
        api.changeFailBehaviour(true)

        onView(withId(R.id.btn_search))
            .perform(click())

        onView(withId(R.id.rv_result))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rv_result))
            .check(withItemCount(0))

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(
                matches(
                    withText(
                        ApplicationProvider.getApplicationContext<Context>().getString(
                            R.string.text_error_fetching, "null"
                        )
                    )
                )
            )
    }
}