package shvyn22.animesearch.api

import okhttp3.MultipartBody
import shvyn22.animesearch.data.remote.AnimeInfo
import shvyn22.animesearch.util.ERROR_FETCHING

class FakeApiInterface(
    private var shouldFail: Boolean = false
): ApiInterface {

    fun changeFailBehaviour(shouldFail: Boolean) {
        this.shouldFail = shouldFail
    }

    private val animeInfos = mutableListOf<AnimeInfo>()

    fun initResponse(items: List<AnimeInfo>) {
        animeInfos.clear()
        animeInfos.addAll(items)
    }

    override suspend fun searchImage(
        image: MultipartBody.Part
    ): ApiResponse<AnimeInfo> {
        return if (shouldFail || animeInfos.isEmpty())
            ApiResponse(error = ERROR_FETCHING, result = emptyList())
        else
            ApiResponse(error = "", result = animeInfos)
    }
}