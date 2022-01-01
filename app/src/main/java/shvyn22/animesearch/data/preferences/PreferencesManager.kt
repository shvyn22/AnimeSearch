package shvyn22.animesearch.data.preferences

import kotlinx.coroutines.flow.Flow


interface PreferencesManager {
    val nightMode: Flow<Int>

    suspend fun editNightMode(nightMode: Int)
}