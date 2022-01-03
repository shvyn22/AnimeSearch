package shvyn22.animesearch.data.util

import shvyn22.animesearch.data.local.model.AnimeModel
import shvyn22.animesearch.data.remote.AnimeInfo

fun fromInfoToModel(item: AnimeInfo) : AnimeModel =
    AnimeModel(
        id = item.anilistInfo.id,
        title = item.anilistInfo.title.toString(),
        isAdult = item.anilistInfo.isAdult,
        image = item.image
    )