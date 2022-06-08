package shvyn22.animesearch.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import shvyn22.animesearch.data.local.dao.BookmarkDao
import shvyn22.animesearch.data.local.dao.FakeBookmarkDao
import shvyn22.animesearch.data.local.model.Bookmark
import shvyn22.animesearch.repository.local.LocalRepository
import shvyn22.animesearch.util.Resource
import shvyn22.animesearch.util.ResourceError

class FakeLocalRepository(
    private val bookmarkDao: BookmarkDao = FakeBookmarkDao()
) : LocalRepository<Bookmark> {

    override suspend fun getBookmarks(): Flow<Resource<List<Bookmark>>> =
        bookmarkDao.getBookmarks().map { items ->
            if (items.isEmpty()) Resource.Error(ResourceError.NoBookmarks, items)
            else Resource.Success(items)
        }

    override suspend fun insertBookmark(item: Bookmark) =
        bookmarkDao.insertBookmark(item)

    override suspend fun deleteBookmark(id: Int) =
        bookmarkDao.deleteBookmark(id)

    override suspend fun deleteBookmarks() =
        bookmarkDao.deleteBookmarks()
}