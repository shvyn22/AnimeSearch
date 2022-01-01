package shvyn22.animesearch.util

sealed class Resource<T> {
    data class Success<T>(val data: T): Resource<T>()
    class Loading<T>(): Resource<T>()
    data class Error<T>(val msg: String): Resource<T>()
}
