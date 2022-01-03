package shvyn22.animesearch.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import shvyn22.animesearch.data.local.dao.BookmarkDao
import shvyn22.animesearch.data.local.dao.FakeBookmarkDao
import shvyn22.animesearch.data.local.model.AnimeModel
import shvyn22.animesearch.repository.local.LocalRepository
import shvyn22.animesearch.util.Resource

class FakeLocalRepository(
    private val bookmarkDao: BookmarkDao = FakeBookmarkDao()
): LocalRepository<AnimeModel> {

    override suspend fun getItems(): Flow<Resource<List<AnimeModel>>> =
        bookmarkDao.getItems().map { items ->
            if (items.isEmpty()) Resource.Error("")
            else Resource.Success(items)
        }

    override suspend fun insertItem(item: AnimeModel) = bookmarkDao.insert(item)

    override suspend fun deleteItem(id: Int) = bookmarkDao.delete(id)

    override suspend fun deleteItems() = bookmarkDao.deleteAll()
}