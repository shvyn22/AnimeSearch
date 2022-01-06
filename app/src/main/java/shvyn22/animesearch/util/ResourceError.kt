package shvyn22.animesearch.util

sealed class ResourceError {
    object Fetching: ResourceError()
    object NoBookmarks: ResourceError()
    data class Specified(val msg: String): ResourceError()
}