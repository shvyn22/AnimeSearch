package shvyn22.animesearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import shvyn22.animesearch.data.preferences.PreferencesManager
import shvyn22.animesearch.util.toLiveData
import javax.inject.Inject

class MainViewModel @Inject constructor(
	private val preferences: PreferencesManager
) : ViewModel() {

	val nightMode = preferences
		.nightMode
		.subscribeOn(Schedulers.io())
		.toLiveData()

	fun onToggleModeIcon() {
		viewModelScope.launch {
			preferences.editNightMode(!(nightMode.value ?: false))
		}
	}
}