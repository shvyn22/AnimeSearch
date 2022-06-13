package shvyn22.animesearch.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map

class PreferencesManagerImpl(
    private val dataStore: DataStore<Preferences>
) : PreferencesManager {

    override val isDarkTheme = dataStore.data.map {
        it[PreferencesKeys.DARK_THEME] ?: false
    }

    override suspend fun editThemePreferences(newThemeValue: Boolean) {
        dataStore.edit {
            it[PreferencesKeys.DARK_THEME] = newThemeValue
        }
    }

    private object PreferencesKeys {
        val DARK_THEME = booleanPreferencesKey("isDarkTheme")
    }
}