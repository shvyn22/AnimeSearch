package shvyn22.animesearch.data.preferences

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.rxjava3.RxDataStore
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class PreferencesManagerImpl(
    private val dataStore: RxDataStore<Preferences>
) : PreferencesManager {

    override val isDarkTheme: Observable<Boolean> = dataStore.data().map {
        it[PreferencesKeys.DARK_THEME] ?: false
    }.toObservable()

    override fun editThemePreferences(newThemeValue: Boolean) {
        dataStore.updateDataAsync {
            val mutablePrefs = it.toMutablePreferences()
            mutablePrefs[PreferencesKeys.DARK_THEME] = newThemeValue
            Single.just(mutablePrefs)
        }
    }

    private object PreferencesKeys {
        val DARK_THEME = booleanPreferencesKey("isDarkTheme")
    }
}