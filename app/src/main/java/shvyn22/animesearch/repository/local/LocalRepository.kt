package shvyn22.animesearch.repository.local

import kotlinx.coroutines.flow.Flow
import shvyn22.animesearch.util.Resource

interface LocalRepository<T> {

    suspend fun getItems(): Flow<Resource<List<T>>>

    suspend fun insertItem(item: T)

    suspend fun deleteItem(id: Int)

    suspend fun deleteItems()
}