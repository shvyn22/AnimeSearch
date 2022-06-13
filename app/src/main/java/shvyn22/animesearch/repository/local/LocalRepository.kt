package shvyn22.animesearch.repository.local

import kotlinx.coroutines.flow.Flow
import shvyn22.animesearch.util.Resource

interface LocalRepository<T> {

    suspend fun getBookmarks(): Flow<Resource<List<T>>>

    suspend fun insertBookmark(item: T)

    suspend fun deleteBookmark(id: Int)

    suspend fun deleteBookmarks()
}