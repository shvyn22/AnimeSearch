package shvyn22.animesearch.repository.remote

import kotlinx.coroutines.flow.Flow
import shvyn22.animesearch.util.Resource
import java.io.InputStream

interface RemoteRepository<T> {

    suspend fun searchImage(inputStream: InputStream): Flow<Resource<List<T>>>
}