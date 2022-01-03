package shvyn22.animesearch.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import shvyn22.animesearch.api.FakeApiInterface
import shvyn22.animesearch.data.remote.AnimeInfo
import shvyn22.animesearch.repository.remote.RemoteRepository
import shvyn22.animesearch.util.Resource
import java.io.InputStream

class FakeRemoteRepository(
    private val api: FakeApiInterface = FakeApiInterface()
): RemoteRepository<AnimeInfo> {

    override suspend fun searchImage(
        inputStream: InputStream
    ): Flow<Resource<List<AnimeInfo>>> = flow {
        emit(Resource.Loading())

        val response = api.searchImage(
            MultipartBody.Part.create(
                RequestBody.create(
                    MediaType.parse("image/*"),
                    ""
                )
            )
        )

        if (response.error.isEmpty())
            emit(Resource.Success(response.result))
        else
            emit(Resource.Error(response.error))
    }
}