package shvyn22.animesearch.data.remote.api

import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import shvyn22.animesearch.data.remote.dto.AnimeDTO

interface ApiService {

    @Multipart
    @POST("search?anilistInfo")
    suspend fun searchImage(
        @Part image: MultipartBody.Part
    ): ApiResponse<AnimeDTO>
}