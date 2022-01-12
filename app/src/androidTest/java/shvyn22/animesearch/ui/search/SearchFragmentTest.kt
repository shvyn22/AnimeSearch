package shvyn22.animesearch.ui.search

import android.content.Context
import androidx.activity.result.ActivityResultRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.endsWith
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.animesearch.R
import shvyn22.animesearch.api.FakeApiInterface
import shvyn22.animesearch.ui.util.MainFragmentFactory
import shvyn22.animesearch.util.*
import javax.inject.Inject

@HiltAndroidTest
class SearchFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var api: FakeApiInterface

    @Inject
    lateinit var registry: ActivityResultRegistry

    @Before
    fun init() {
        hiltRule.inject()

        launchFragmentInHiltContainer<SearchFragment>(
            fragmentFactory = MainFragmentFactory(registry)
        )
    }

    @Test
    fun remoteIsAvailable_populateApiWith2Items_2ItemsAreInView() {
        api.initResponse(animeDTOs)

        onView(withId(R.id.btn_from_file))
            .perform(click())

        onView(withId(R.id.btn_search))
            .perform(click())

        onView(withId(R.id.rv_result))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rv_result))
            .check(withItemCount(2))

        onView(withText(endsWith(animeDTO1.anilistInfo.title.toString())))
            .check(matches(isDisplayed()))

        onView(withText(endsWith(animeDTO2.anilistInfo.title.toString())))
            .check(matches(isDisplayed()))
    }

    @Test
    fun remoteIsAvailable_populateApiWithNoItems_ErrorIsThrown() {
        onView(withId(R.id.btn_from_file))
            .perform(click())

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
                        endsWith(
                            ApplicationProvider
                                .getApplicationContext<Context>()
                                .getString(R.string.text_error_fetching, "null")
                        )
                    )
                )
            )
    }

    @Test
    fun remoteIsNotAvailable_ErrorIsThrown() {
        api.changeFailBehaviour(true)

        onView(withId(R.id.btn_from_file))
            .perform(click())

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
                        endsWith(
                            ApplicationProvider
                                .getApplicationContext<Context>()
                                .getString(R.string.text_error_fetching, "null")
                        )
                    )
                )
            )
    }
}