package shvyn22.animesearch.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map

class PreferencesManagerImpl(
    private val dataStore: DataStore<Preferences>
) : PreferencesManager {

    override val nightMode = dataStore.data.map {
        it[PreferencesKeys.NIGHT_MODE] ?: false
    }

    override suspend fun editNightMode(nightMode: Boolean) {
        dataStore.edit {
            it[PreferencesKeys.NIGHT_MODE] = nightMode
        }
    }

    private object PreferencesKeys {
        val NIGHT_MODE = booleanPreferencesKey("nightMode")
    }
}