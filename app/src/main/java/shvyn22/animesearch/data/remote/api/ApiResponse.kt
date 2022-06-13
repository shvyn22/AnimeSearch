package shvyn22.animesearch.data.remote.api

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(

    @SerializedName("error")
    val error: String,

    @SerializedName("result")
    val result: List<T>
)