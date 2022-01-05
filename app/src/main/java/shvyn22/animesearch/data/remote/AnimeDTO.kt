package shvyn22.animesearch.data.remote

import com.google.gson.annotations.SerializedName
import shvyn22.animesearch.util.getPercentage
import shvyn22.animesearch.util.toStringTime

data class AnimeDTO(
    @SerializedName("anilist")
    val anilistInfo: AnilistInfo,

    @SerializedName("episode")
    val episode: Int?,

    @SerializedName("from")
    val from: Float,

    @SerializedName("to")
    val to: Float,

    @SerializedName("similarity")
    val similarity: Float,

    @SerializedName("image")
    val image: String,
) {

    fun getSimilarityPercentage() : Int {
        return similarity.getPercentage()
    }

    fun getFragmentRange() : Pair<String, String> {
        return Pair(from.toStringTime(), to.toStringTime())
    }
}

data class AnilistInfo(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: TitleInfo,

    @SerializedName("isAdult")
    val isAdult: Boolean,
)

data class TitleInfo(
    @SerializedName("native")
    val native: String,

    @SerializedName("romaji")
    val romaji: String,

    @SerializedName("english")
    val english: String?,
) {

    override fun toString(): String {
        return if (english == null)
            "$native ($romaji)"
        else
            "$native ($english)"
    }
}