package shvyn22.animesearch.repository.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import shvyn22.animesearch.data.local.dao.BookmarkDao
import shvyn22.animesearch.data.local.model.Bookmark
import shvyn22.animesearch.util.Resource
import shvyn22.animesearch.util.ResourceError

class LocalRepositoryImpl(
    private val dao: BookmarkDao
) : LocalRepository<Bookmark> {

    override suspend fun getBookmarks(): Flow<Resource<List<Bookmark>>> =
        dao.getBookmarks().map { items ->
            if (items.isEmpty())
                Resource.Error(ResourceError.NoBookmarks, items)
            else
                Resource.Success(items)
        }

    override suspend fun insertBookmark(item: Bookmark) =
        dao.insertBookmark(item)

    override suspend fun deleteBookmark(id: Int) =
        dao.deleteBookmark(id)

    override suspend fun deleteBookmarks() =
        dao.deleteBookmarks()
}