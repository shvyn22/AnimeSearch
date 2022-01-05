package shvyn22.animesearch.data.local.model

data class AnimeModel(
    val id: Int,
    val title: String,
    val episode: Int,
    val range: Pair<String, String>,
    val similarity: Int,
    val isAdult: Boolean,
    val image: String,
    val isBookmarked: Boolean,
)