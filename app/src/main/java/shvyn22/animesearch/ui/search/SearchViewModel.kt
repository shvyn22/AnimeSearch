package shvyn22.animesearch.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import shvyn22.animesearch.data.local.model.AnimeModel
import shvyn22.animesearch.data.local.model.Bookmark
import shvyn22.animesearch.data.util.fromAnimeModelToBookmark
import shvyn22.animesearch.repository.local.LocalRepository
import shvyn22.animesearch.repository.remote.RemoteRepository
import shvyn22.animesearch.util.ErrorType
import shvyn22.animesearch.util.Resource
import shvyn22.animesearch.util.StateEvent
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository<AnimeModel>,
    private val localRepository: LocalRepository<Bookmark>
) : ViewModel() {

    private val _events = Channel<StateEvent>()
    val events = _events.receiveAsFlow()

    private val _uri = MutableStateFlow("")
    val uri = _uri.asStateFlow()

    private val _searchResults = MutableStateFlow<Resource<List<AnimeModel>>>(Resource.Idle())
    val searchResults = _searchResults.asStateFlow()

    fun updateSelectedImage(newBytes: String) {
        _uri.value = newBytes
    }

    fun searchImage(byteArray: ByteArray) {
        viewModelScope.launch {
            remoteRepository.searchImage(byteArray).collect {
                _searchResults.value = it
            }
        }
    }

    fun onAddToBookmarks(item: AnimeModel) {
        viewModelScope.launch {
            localRepository.insertItem(
                fromAnimeModelToBookmark(item)
            )
        }
    }

    fun onRemoveFromBookmarks(id: Int) {
        viewModelScope.launch {
            localRepository.deleteItem(id)
        }
    }

    fun onNavigateToAnilist(id: Int) {
        viewModelScope.launch {
            _events.send(StateEvent.NavigateToAnilist(id))
        }
    }

    fun onErrorOccurred(error: ErrorType) {
        viewModelScope.launch {
            _events.send(StateEvent.ShowError(error))
        }
    }
}