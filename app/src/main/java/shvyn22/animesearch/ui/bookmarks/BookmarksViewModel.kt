package shvyn22.animesearch.ui.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import shvyn22.animesearch.data.local.model.Bookmark
import shvyn22.animesearch.repository.local.LocalRepository
import shvyn22.animesearch.util.ErrorType
import shvyn22.animesearch.util.StateEvent
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val localRepository: LocalRepository<Bookmark>,
) : ViewModel() {

    private val eventsChannel = MutableSharedFlow<StateEvent>()
    val events = eventsChannel.asSharedFlow()

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
            eventsChannel.emit(StateEvent.NavigateToAnilist(id))
        }
    }

    fun onErrorOccurred(error: ErrorType) {
        viewModelScope.launch {
            eventsChannel.emit(StateEvent.ShowError(error))
        }
    }
}