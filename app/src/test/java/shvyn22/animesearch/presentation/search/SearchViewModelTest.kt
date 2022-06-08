package shvyn22.animesearch.presentation.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.animesearch.data.remote.api.FakeApiService
import shvyn22.animesearch.data.local.dao.FakeBookmarkDao
import shvyn22.animesearch.data.util.fromAnimeDTOToModel
import shvyn22.animesearch.repository.FakeLocalRepository
import shvyn22.animesearch.repository.FakeRemoteRepository
import shvyn22.animesearch.util.*

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var api: FakeApiService
    private lateinit var dao: FakeBookmarkDao
    private lateinit var viewModel: SearchViewModel

    @Before
    fun init() {
        api = FakeApiService()
        dao = FakeBookmarkDao()
        viewModel = SearchViewModel(
            FakeRemoteRepository(api, dao),
            FakeLocalRepository(dao)
        )
    }

    @Test
    fun updateUri_ReturnsValidOne() = runTest {
        viewModel.updateSelectedImage(IMAGE_URI)

        val uri = viewModel.uri.value

        assertThat(uri, `is`(IMAGE_URI))
    }

    @Test
    fun remoteIsAvailable_populateApiWith2Items_Returns2Items() = runTest {
        api.initResponse(animeDTOs)

        viewModel.searchImage(byteArrayOf())
        val items = viewModel.searchResults.drop(1).first()

        assertThat(
            items,
            `is`(instanceOf(Resource.Success::class.java))
        )

        assertThat(
            (items as Resource.Success).data,
            `is`(fromAnimeDTOToModel(animeDTOs))
        )
    }

    @Test
    fun remoteIsAvailable_populateApiWithNoItems_ReturnsError() = runTest {
        viewModel.searchImage(byteArrayOf())
        val items = viewModel.searchResults.drop(1).first()

        assertThat(
            items,
            `is`(instanceOf(Resource.Error::class.java))
        )

        assertThat(
            (items as Resource.Error).error,
            `is`(instanceOf(ResourceError.Fetching::class.java))
        )
    }

    @Test
    fun remoteIsNotAvailable_ReturnsError() = runTest {
        api.changeFailBehaviour(false)

        viewModel.searchImage(byteArrayOf())
        val items = viewModel.searchResults.drop(1).first()

        assertThat(
            items,
            `is`(instanceOf(Resource.Error::class.java))
        )

        assertThat(
            (items as Resource.Error).error,
            `is`(instanceOf(ResourceError.Fetching::class.java))
        )
    }

    @Test
    fun toggleInBookmarks_ReturnsValidState() = runTest {
        viewModel.insertBookmark(fromAnimeDTOToModel(animeDTO1))
        var bookmarks = dao.getBookmarks().drop(1).first()

        assertThat(
            bookmarks.size,
            `is`(1)
        )

        assertThat(
            bookmarks[0].id,
            `is`(animeDTO1.anilistInfo.id)
        )

        viewModel.deleteBookmark(animeDTO1.anilistInfo.id)
        bookmarks = dao.getBookmarks().drop(1).first()

        assertThat(
            bookmarks.size,
            `is`(0)
        )
    }
}