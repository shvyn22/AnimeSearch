package shvyn22.animesearch.data.preferences

import androidx.appcompat.app.AppCompatDelegate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakePreferencesManager: PreferencesManager {

    private val _nightMode = MutableStateFlow(false)

    override val nightMode: Flow<Boolean> get() = _nightMode

    override suspend fun editNightMode(nightMode: Boolean) {
        _nightMode.value = nightMode
    }
}