package shvyn22.animesearch.data.local.dao

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import shvyn22.animesearch.data.local.model.Bookmark

class FakeBookmarkDao: BookmarkDao {

    private val bookmarks = mutableListOf<Bookmark>()

    override fun getItems(): Flow<List<Bookmark>> = flow {
        emit(bookmarks)
    }

    override fun exists(id: Int): Boolean =
        bookmarks.find { it.id == id } != null

    override suspend fun insert(item: Bookmark) {
        bookmarks.add(item)
    }

    override suspend fun delete(id: Int) {
        bookmarks.removeIf { it.id == id }
    }

    override suspend fun deleteAll() {
        bookmarks.clear()
    }
}