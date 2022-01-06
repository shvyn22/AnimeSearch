package shvyn22.animesearch.data.local.dao

import kotlinx.coroutines.flow.*
import shvyn22.animesearch.data.local.model.Bookmark

class FakeBookmarkDao: BookmarkDao {

    private val _bookmarks = MutableStateFlow(listOf<Bookmark>())
    private val bookmarks get() = _bookmarks.asStateFlow()

    override fun getItems(): Flow<List<Bookmark>> = flow {
        bookmarks.collect { emit(it) }
    }

    override suspend fun insert(item: Bookmark) {
        val newBookmarks = _bookmarks.value.toMutableList()
        _bookmarks.value = newBookmarks.also { it.add(item) }
    }

    override suspend fun delete(id: Int) {
        val newBookmarks = _bookmarks.value.toMutableList()
        _bookmarks.value = newBookmarks.also {
            it.removeIf { bookmark -> bookmark.id == id }
        }
    }

    override suspend fun deleteAll() {
        _bookmarks.value = emptyList()
    }
}