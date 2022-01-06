package shvyn22.animesearch.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import shvyn22.animesearch.api.FakeApiInterface
import shvyn22.animesearch.data.local.dao.BookmarkDao
import shvyn22.animesearch.data.local.dao.FakeBookmarkDao
import shvyn22.animesearch.data.local.model.AnimeModel
import shvyn22.animesearch.data.util.fromAnimeDTOToModel
import shvyn22.animesearch.repository.remote.RemoteRepository
import shvyn22.animesearch.util.ResourceError
import shvyn22.animesearch.util.Resource

class FakeRemoteRepository(
    private val api: FakeApiInterface = FakeApiInterface(),
    private val bookmarkDao: BookmarkDao = FakeBookmarkDao(),
) : RemoteRepository<AnimeModel> {

    override suspend fun searchImage(
        bytes: ByteArray
    ): Flow<Resource<List<AnimeModel>>> = flow {
        emit(Resource.Loading())

        val response = api.searchImage(
            MultipartBody.Part.create(
                RequestBody.create(
                    MediaType.parse("image/*"), ""
                )
            )
        )

        if (response.error.isEmpty())
            bookmarkDao.getItems().collect { bookmarks ->
                emit(
                    Resource.Success(
                        fromAnimeDTOToModel(response.result)
                            .distinctBy { it.id }
                            .map { model ->
                                model.copy(isBookmarked = bookmarks.any {
                                    it.id == model.id
                                })
                            }
                    )
                )
            }
        else
            emit(Resource.Error(ResourceError.Fetching))
    }
}