package shvyn22.animesearch.api

import okhttp3.MultipartBody
import shvyn22.animesearch.data.remote.AnimeDTO
import shvyn22.animesearch.util.ERROR_FETCHING

class FakeApiInterface(
    private var shouldFail: Boolean = false
): ApiInterface {

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