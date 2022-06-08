package shvyn22.animesearch.data.remote.api

import io.reactivex.rxjava3.core.Observable
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import shvyn22.animesearch.data.remote.dto.AnimeDTO

interface ApiService {

    @Multipart
    @POST("search?anilistInfo")
    fun searchImage(
        @Part image: MultipartBody.Part
    ): Observable<ApiResponse<AnimeDTO>>
}