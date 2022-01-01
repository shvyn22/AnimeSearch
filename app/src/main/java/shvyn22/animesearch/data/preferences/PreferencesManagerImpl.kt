package shvyn22.animesearch.data.preferences

import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.map

class PreferencesManagerImpl(
    private val dataStore: DataStore<Preferences>
) : PreferencesManager {

    override val nightMode = dataStore.data.map {
        it[PreferencesKeys.NIGHT_MODE] ?: AppCompatDelegate.getDefaultNightMode()
    }

    override suspend fun editNightMode(nightMode: Int) {
        dataStore.edit {
            it[PreferencesKeys.NIGHT_MODE] = nightMode
        }
    }

    private object PreferencesKeys {
        val NIGHT_MODE = intPreferencesKey("nightMode")
    }
}