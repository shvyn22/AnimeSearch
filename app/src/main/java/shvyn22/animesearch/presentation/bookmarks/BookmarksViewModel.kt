package shvyn22.animesearch.presentation.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import shvyn22.animesearch.data.local.model.Bookmark
import shvyn22.animesearch.repository.local.LocalRepository
import shvyn22.animesearch.util.ResourceError
import shvyn22.animesearch.util.StateEvent
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
	private val localRepository: LocalRepository<Bookmark>,
) : ViewModel() {

	private val _events = Channel<StateEvent>()
	val events = _events.receiveAsFlow()

	fun getBookmarks() = flow {
		localRepository.getItems().collect {
			emit(it)
		}
	}

	fun onRemoveFromBookmarks(id: Int) {
		viewModelScope.launch {
			localRepository.deleteItem(id)
		}
	}

	fun onRemoveAllFromBookmarks() {
		viewModelScope.launch {
			localRepository.deleteItems()
		}
	}

	fun onNavigateToAnilist(id: Int) {
		viewModelScope.launch {
			_events.send(StateEvent.NavigateToAnilist(id))
		}
	}

	fun onErrorOccurred(error: ResourceError) {
		viewModelScope.launch {
			_events.send(StateEvent.ShowError(error))
		}
	}
}