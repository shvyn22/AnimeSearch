package shvyn22.animesearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import shvyn22.animesearch.data.preferences.PreferencesManager
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferences: PreferencesManager
) : ViewModel() {

    val isDarkTheme = preferences.isDarkTheme
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    fun editThemePreferences(newThemeValue: Boolean) {
        viewModelScope.launch {
            preferences.editThemePreferences(newThemeValue)
        }
    }
}