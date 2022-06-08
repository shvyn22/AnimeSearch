package shvyn22.animesearch.data.remote.api

import okhttp3.MultipartBody
import shvyn22.animesearch.data.remote.dto.AnimeDTO
import shvyn22.animesearch.util.ERROR_FETCHING

class FakeApiService(
    private var shouldFail: Boolean = false
) : ApiService {

    fun changeFailBehaviour(shouldFail: Boolean) {
        this.shouldFail = shouldFail
    }

    private val animeDTOs = mutableListOf<AnimeDTO>()

    fun initResponse(items: List<AnimeDTO>) {
        animeDTOs.clear()
        animeDTOs.addAll(items)
    }

    override suspend fun searchImage(
        image: MultipartBody.Part
    ): ApiResponse<AnimeDTO> {
        return if (shouldFail || animeDTOs.isEmpty())
            ApiResponse(error = ERROR_FETCHING, result = emptyList())
        else
            ApiResponse(error = "", result = animeDTOs)
    }
}