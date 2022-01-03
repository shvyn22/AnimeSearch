package shvyn22.animesearch.data.preferences

import androidx.appcompat.app.AppCompatDelegate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakePreferencesManager: PreferencesManager {

    private val _nightMode = MutableStateFlow(AppCompatDelegate.MODE_NIGHT_NO)

    override val nightMode: Flow<Int> get() = _nightMode

    override suspend fun editNightMode(nightMode: Int) {
        _nightMode.value = nightMode
    }
}