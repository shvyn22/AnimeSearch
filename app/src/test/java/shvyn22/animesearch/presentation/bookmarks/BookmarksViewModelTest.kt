package shvyn22.animesearch.presentation.bookmarks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.animesearch.data.local.dao.FakeBookmarkDao
import shvyn22.animesearch.repository.FakeLocalRepository
import shvyn22.animesearch.util.MainCoroutineRule
import shvyn22.animesearch.util.Resource
import shvyn22.animesearch.util.bookmark1
import shvyn22.animesearch.util.bookmark2

@ExperimentalCoroutinesApi
class BookmarksViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var dao: FakeBookmarkDao
    private lateinit var viewModel: BookmarksViewModel

    @Before
    fun init() {
        dao = FakeBookmarkDao()
        viewModel = BookmarksViewModel(
            FakeLocalRepository(dao)
        )
    }

    @Test
    fun populateDaoWith2Items_Returns2Items() = runTest {
        dao.insertBookmark(bookmark1)
        dao.insertBookmark(bookmark2)

        val resource = viewModel.getBookmarks().first()
        assertThat(resource, `is`(instanceOf(Resource.Success::class.java)))

        val bookmarks = (resource as Resource.Success).data
        assertThat(bookmarks.size, `is`(2))
        assertThat(bookmarks[0].id, `is`(bookmark1.id))
        assertThat(bookmarks[1].id, `is`(bookmark2.id))
    }

    @Test
    fun populateDaoWithNoItems_ReturnsError() = runTest {
        val resource = viewModel.getBookmarks().first()
        assertThat(resource, `is`(instanceOf(Resource.Error::class.java)))
    }

    @Test
    fun populateDaoWith2Items_Remove1Item_Returns1Item() = runTest {
        dao.insertBookmark(bookmark1)
        dao.insertBookmark(bookmark2)

        viewModel.deleteBookmark(bookmark1.id)

        val bookmarks = dao.getBookmarks().drop(1).first()
        assertThat(bookmarks.size, `is`(1))
        assertThat(bookmarks[0].id, `is`(bookmark2.id))
    }

    @Test
    fun populateDaoWith2Items_RemoveAllItems_ReturnsNoItems() = runTest {
        dao.insertBookmark(bookmark1)
        dao.insertBookmark(bookmark2)

        viewModel.deleteBookmarks()

        val bookmarks = dao.getBookmarks().drop(1).first()
        assertThat(bookmarks.size, `is`(0))
    }
}