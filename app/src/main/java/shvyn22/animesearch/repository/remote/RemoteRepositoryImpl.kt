package shvyn22.animesearch.repository.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import shvyn22.animesearch.api.ApiInterface
import shvyn22.animesearch.data.remote.AnimeInfo
import shvyn22.animesearch.util.Resource
import java.io.InputStream
import java.lang.Exception

class RemoteRepositoryImpl(
    private val api: ApiInterface
) : RemoteRepository<AnimeInfo> {

    override suspend fun searchImage(
        inputStream: InputStream
    ): Flow<Resource<List<AnimeInfo>>> = flow {
        emit(Resource.Loading())

        try {
            val image = MultipartBody.Part.createFormData(
                "image", "image", RequestBody.create(
                    MediaType.parse("image/*"),
                    inputStream.readBytes()
                )
            )
            val response = api.searchImage(image)

            if (response.error.isEmpty())
                emit(Resource.Success(response.result))
            else
                emit(Resource.Error(response.error))
        } catch (e: Exception) {
            emit(Resource.Error(""))
        }
    }
}