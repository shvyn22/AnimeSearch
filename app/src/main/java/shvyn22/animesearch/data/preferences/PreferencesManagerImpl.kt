package shvyn22.animesearch.data.preferences

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.rxjava3.RxDataStore
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class PreferencesManagerImpl(
	private val dataStore: RxDataStore<Preferences>
) : PreferencesManager {

	override val nightMode: Observable<Boolean> = dataStore.data().map {
		it[PreferencesKeys.NIGHT_MODE] ?: false
	}.toObservable()

	override fun editNightMode(nightMode: Boolean) {
		dataStore.updateDataAsync {
			val mutablePrefs = it.toMutablePreferences()
			mutablePrefs[PreferencesKeys.NIGHT_MODE] = nightMode
			Single.just(mutablePrefs)
		}
	}

	private object PreferencesKeys {
		val NIGHT_MODE = booleanPreferencesKey("nightMode")
	}
}