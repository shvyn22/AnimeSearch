package shvyn22.animesearch.data.local.dao

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import shvyn22.animesearch.data.local.model.AnimeModel

class FakeBookmarkDao: BookmarkDao {

    private val bookmarks = mutableListOf<AnimeModel>()

    override suspend fun getItems(): Flow<List<AnimeModel>> = flow {
        emit(bookmarks)
    }

    override suspend fun insert(item: AnimeModel) {
        bookmarks.add(item)
    }

    override suspend fun delete(id: Int) {
        bookmarks.removeIf { it.id == id }
    }

    override suspend fun deleteAll() {
        bookmarks.clear()
    }
}