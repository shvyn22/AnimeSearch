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

    private val animeInfos = mutableListOf<AnimeDTO>()

    fun initResponse(items: List<AnimeDTO>) {
        animeInfos.clear()
        animeInfos.addAll(items)
    }

    override suspend fun searchImage(
        image: MultipartBody.Part
    ): ApiResponse<AnimeDTO> {
        return if (shouldFail || animeInfos.isEmpty())
            ApiResponse(error = ERROR_FETCHING, result = emptyList())
        else
            ApiResponse(error = "", result = animeInfos)
    }
}