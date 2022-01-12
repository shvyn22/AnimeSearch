package shvyn22.animesearch.ui.bookmarks

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.animesearch.R
import shvyn22.animesearch.data.local.dao.BookmarkDao
import shvyn22.animesearch.util.bookmark1
import shvyn22.animesearch.util.bookmark2
import shvyn22.animesearch.util.launchFragmentInHiltContainer
import shvyn22.animesearch.util.withItemCount
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
class BookmarksFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var bookmarkDao: BookmarkDao

    @Before
    fun init() {
        hiltRule.inject()

        launchFragmentInHiltContainer<BookmarksFragment> { }
    }

    @Test
    fun populateDaoWith2Items_2ItemsAreInView() {
        runTest {
            bookmarkDao.insert(bookmark1)
            bookmarkDao.insert(bookmark2)
        }

        onView(withId(R.id.rv_bookmarks))
            .check(matches(isDisplayed()))
            .check(withItemCount(2))

        onView(withText(bookmark1.title))
            .check(matches(isDisplayed()))

        onView(withText(bookmark2.title))
            .check(matches(isDisplayed()))
    }

    @Test
    fun populateDaoWith1Item_1ItemIsInView() {
        runTest {
            bookmarkDao.insert(bookmark1)
        }

        onView(withId(R.id.rv_bookmarks))
            .check(matches(isDisplayed()))
            .check(withItemCount(1))

        onView(withText(bookmark1.title))
            .check(matches(isDisplayed()))
    }

    @Test
    fun populateDaoWithNoItems_NoItemsAreInView() {
        onView(withId(R.id.rv_bookmarks))
            .check(matches(isDisplayed()))
            .check(withItemCount(0))
    }

    @Test
    fun populateDaoWith2Items_Swipe1Item_1ItemIsInView() {
        runTest {
            bookmarkDao.insert(bookmark1)
            bookmarkDao.insert(bookmark2)
        }

        onView(withId(R.id.rv_bookmarks))
            .perform(
                actionOnItemAtPosition<BookmarksAdapter.BookmarksViewHolder>(
                    0,
                    swipeRight()
                )
            )

        onView(withId(R.id.rv_bookmarks))
            .check(withItemCount(1))

        onView(withText(bookmark1.title))
            .check(matches(isDisplayed()))
    }

    @Test
    fun populateDaoWith2Items_RemoveAllItems_NoItemsAreInView() {
        runTest {
            bookmarkDao.insert(bookmark1)
            bookmarkDao.insert(bookmark2)
        }

        onView(withId(R.id.action_delete))
            .perform(click())

        onView(withId(R.id.rv_bookmarks))
            .check(matches(isDisplayed()))
            .check(withItemCount(0))
    }
}