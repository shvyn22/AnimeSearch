package shvyn22.animesearch.data.preferences

import kotlinx.coroutines.flow.Flow


interface PreferencesManager {

    val nightMode: Flow<Boolean>

    suspend fun editNightMode(nightMode: Boolean)
}