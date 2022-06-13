package shvyn22.animesearch.presentation.bookmarks

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.animesearch.R
import shvyn22.animesearch.data.local.dao.BookmarkDao
import shvyn22.animesearch.di.tearDownPreferencesDependencies
import shvyn22.animesearch.presentation.MainActivity
import shvyn22.animesearch.util.bookmark1
import shvyn22.animesearch.util.bookmark2
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
class BookmarksScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var bookmarkDao: BookmarkDao

    @Inject
    lateinit var scope: CoroutineScope

    @Before
    fun init() {
        hiltRule.inject()

        composeRule.apply {
            onNodeWithText(activity.getString(R.string.text_navigate_to_bookmarks))
                .performClick()
        }
    }

    @After
    fun tearDown() {
        tearDownPreferencesDependencies(scope)
    }

    @Test
    fun populateDaoWith2Items_2ItemsAreInView() {
        runTest {
            bookmarkDao.insertBookmark(bookmark1)
            bookmarkDao.insertBookmark(bookmark2)
        }

        composeRule.apply {
            onNodeWithText(activity.getString(R.string.text_title, bookmark1.title))
                .assertIsDisplayed()

            onNodeWithText(activity.getString(R.string.text_title, bookmark2.title))
                .assertIsDisplayed()
        }
    }

    @Test
    fun populateDaoWith1Item_1ItemIsInView() {
        runTest {
            bookmarkDao.insertBookmark(bookmark1)
        }

        composeRule.apply {
            onNodeWithText(activity.getString(R.string.text_title, bookmark1.title))
                .assertIsDisplayed()

            onNodeWithText(activity.getString(R.string.text_title, bookmark2.title))
                .assertDoesNotExist()
        }
    }

    @Test
    fun populateDaoWithNoItems_NoItemsAreInView() {
        composeRule.apply {
            onNodeWithText(activity.getString(R.string.text_error_no_bookmarks))
                .assertIsDisplayed()

            onNodeWithText(activity.getString(R.string.text_title, bookmark1.title))
                .assertDoesNotExist()

            onNodeWithText(activity.getString(R.string.text_title, bookmark2.title))
                .assertDoesNotExist()
        }
    }

    @Test
    fun populateDaoWith2Items_Swipe1Item_1ItemIsInView() {
        runTest {
            bookmarkDao.insertBookmark(bookmark1)
            bookmarkDao.insertBookmark(bookmark2)
        }

        //TODO: implement swipe

        composeRule.apply {
            onNodeWithText(activity.getString(R.string.text_title, bookmark1.title))
                .assertIsDisplayed()

            onNodeWithText(activity.getString(R.string.text_title, bookmark2.title))
                .assertDoesNotExist()
        }
    }

    @Test
    fun populateDaoWith2Items_RemoveAllItems_NoItemsAreInView() {
        runTest {
            bookmarkDao.insertBookmark(bookmark1)
            bookmarkDao.insertBookmark(bookmark2)
        }

        composeRule.apply {
            onNodeWithText(activity.getString(R.string.text_title, bookmark1.title))
                .assertIsDisplayed()

            onNodeWithText(activity.getString(R.string.text_title, bookmark2.title))
                .assertIsDisplayed()

            onNodeWithContentDescription(activity.getString(R.string.text_accessibility_remove))
                .performClick()

            onNodeWithText(activity.getString(R.string.text_title, bookmark1.title))
                .assertDoesNotExist()

            onNodeWithText(activity.getString(R.string.text_title, bookmark2.title))
                .assertDoesNotExist()
        }
    }
}