package shvyn22.animesearch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import shvyn22.animesearch.data.preferences.PreferencesManager
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferences: PreferencesManager
) : ViewModel() {

    val nightMode = flow {
        preferences.nightMode.collect {
            emit(it)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = false
    )

    fun onToggleModeIcon() {
        viewModelScope.launch {
            preferences.editNightMode(!nightMode.value)
        }
    }
}