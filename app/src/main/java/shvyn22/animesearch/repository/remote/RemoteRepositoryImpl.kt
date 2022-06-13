package shvyn22.animesearch.repository.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import shvyn22.animesearch.data.local.dao.BookmarkDao
import shvyn22.animesearch.data.local.model.AnimeModel
import shvyn22.animesearch.data.remote.api.ApiService
import shvyn22.animesearch.data.util.fromAnimeDTOToModel
import shvyn22.animesearch.util.Resource
import shvyn22.animesearch.util.ResourceError

class RemoteRepositoryImpl(
    private val api: ApiService,
    private val dao: BookmarkDao,
) : RemoteRepository<AnimeModel> {

    override suspend fun searchImage(
        bytes: ByteArray
    ): Flow<Resource<List<AnimeModel>>> = flow {
        emit(Resource.Loading())

        try {
            val image = MultipartBody.Part.createFormData(
                "image", "image", RequestBody.create(
                    MediaType.parse("image/*"), bytes
                )
            )

            val response = api.searchImage(image)
            if (response.error.isEmpty()) {
                dao.getBookmarks().collect { bookmarks ->
                    emit(
                        Resource.Success(
                            fromAnimeDTOToModel(response.result)
                                .distinctBy { it.id }
                                .map { model ->
                                    model.copy(
                                        isBookmarked = bookmarks.any { it.id == model.id }
                                    )
                                }
                        )
                    )
                }
            } else emit(Resource.Error(ResourceError.Specified(response.error)))
        } catch (e: Exception) {
            emit(Resource.Error(ResourceError.Fetching))
        }
    }
}