package shvyn22.animesearch.util

import shvyn22.animesearch.data.local.model.AnimeModel
import shvyn22.animesearch.data.remote.AnilistInfo
import shvyn22.animesearch.data.remote.AnimeInfo
import shvyn22.animesearch.data.remote.TitleInfo

const val ERROR_FETCHING = "error fetching data"
const val TEST_DATASTORE_FILENAME = "test_preferences"
const val IMAGE_URI = ""

val animeInfo1 = AnimeInfo(
    anilistInfo = AnilistInfo(
        id = 21202,
        title = TitleInfo(
            native = "この素晴らしい世界に祝福を！",
            romaji = "Kono Subarashii Sekai ni Shukufuku wo!",
            english = "KONOSUBA -God's blessing on this wonderful world!"
        ),
        isAdult = false
    ),
    episode = 2,
    from = 449.75f,
    to = 450.75f,
    similarity = 0.9197542f,
    image = "https://media.trace.moe/image/21202/Kono%20Subarashii%20Sekai%20ni%20Shukufuku%20o!%20-%2002%20(BD%201280x720%20x264%20AACx2).mp4?t=449.91499999999996&token=YW33Zh61zJQIr9wtz6hipUkzg"
)

val animeInfo2 = AnimeInfo(
    anilistInfo = AnilistInfo(
        id = 3958,
        title = TitleInfo(
            native = "かんなぎ",
            romaji = "Kannagi",
            english = "Kannagi: Crazy Shrine Maidens"
        ),
        isAdult = false
    ),
    episode = 7,
    from = 148.5f,
    to = 150.08f,
    similarity = 0.8155056f,
    image = "https://media.trace.moe/image/3958/%5BLeopard-Raws%5D%20Kannagi%20-%2007%20(BD%201920x1080%20x264%20AAC%20rev).mp4?t=149.29000000000002&token=o0fF5ae1d8uqH4NxdwA2pM9PQ"
)

val bookmark1 = AnimeModel(
    id = 21202,
    title = "この素晴らしい世界に祝福を！(KONOSUBA -God's blessing on this wonderful world!)",
    isAdult = false,
    image = "https://media.trace.moe/image/21202/Kono%20Subarashii%20Sekai%20ni%20Shukufuku%20o!%20-%2002%20(BD%201280x720%20x264%20AACx2).mp4?t=449.91499999999996&token=YW33Zh61zJQIr9wtz6hipUkzg"
)

val bookmark2 = AnimeModel(
    id = 3958,
    title = "かんなぎ (Kannagi: Crazy Shrine Maidens)",
    isAdult = false,
    image = "https://media.trace.moe/image/3958/%5BLeopard-Raws%5D%20Kannagi%20-%2007%20(BD%201920x1080%20x264%20AAC%20rev).mp4?t=149.29000000000002&token=o0fF5ae1d8uqH4NxdwA2pM9PQ"
)