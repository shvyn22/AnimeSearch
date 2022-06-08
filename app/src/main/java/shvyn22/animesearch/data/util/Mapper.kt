package shvyn22.animesearch.data.util

import shvyn22.animesearch.data.local.model.AnimeModel
import shvyn22.animesearch.data.local.model.Bookmark
import shvyn22.animesearch.data.remote.dto.AnimeDTO

fun fromAnimeDTOToModel(item: AnimeDTO): AnimeModel =
    AnimeModel(
        id = item.anilistInfo.id,
        title = item.anilistInfo.title.toString(),
        episode = item.episode ?: 1,
        range = item.getFragmentRange(),
        similarity = item.getSimilarityPercentage(),
        isAdult = item.anilistInfo.isAdult,
        image = item.image,
        isBookmarked = false,
    )

fun fromAnimeDTOToModel(items: List<AnimeDTO>): List<AnimeModel> =
    items.map { fromAnimeDTOToModel(it) }

fun fromAnimeModelToBookmark(item: AnimeModel): Bookmark =
    Bookmark(
        id = item.id,
        title = item.title,
        isAdult = item.isAdult,
        image = item.image
    )