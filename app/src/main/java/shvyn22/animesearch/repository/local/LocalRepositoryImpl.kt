package shvyn22.animesearch.repository.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import shvyn22.animesearch.data.local.dao.BookmarkDao
import shvyn22.animesearch.data.local.model.Bookmark
import shvyn22.animesearch.util.ErrorType
import shvyn22.animesearch.util.Resource

class LocalRepositoryImpl(
    private val bookmarkDao: BookmarkDao
) : LocalRepository<Bookmark> {

    override suspend fun getItems(): Flow<Resource<List<Bookmark>>> =
        bookmarkDao.getItems().map { items ->
            if (items.isEmpty()) Resource.Error(ErrorType.NoBookmarks)
            else Resource.Success(items)
        }

    override suspend fun insertItem(item: Bookmark) = bookmarkDao.insert(item)

    override suspend fun deleteItem(id: Int) = bookmarkDao.delete(id)

    override suspend fun deleteItems() = bookmarkDao.deleteAll()
}