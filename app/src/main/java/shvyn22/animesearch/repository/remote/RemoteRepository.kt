package shvyn22.animesearch.repository.remote

import kotlinx.coroutines.flow.Flow
import shvyn22.animesearch.util.Resource

interface RemoteRepository<T> {

    suspend fun searchImage(bytes: ByteArray): Flow<Resource<List<T>>>
}