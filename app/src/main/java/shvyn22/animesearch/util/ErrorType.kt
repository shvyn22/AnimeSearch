package shvyn22.animesearch.util

sealed class ErrorType {
    object Fetching: ErrorType()
    object NoBookmarks: ErrorType()
    data class Specified(val msg: String): ErrorType()
}