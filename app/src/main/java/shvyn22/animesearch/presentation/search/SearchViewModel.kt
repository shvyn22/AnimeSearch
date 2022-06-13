package shvyn22.animesearch.presentation.search

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
import shvyn22.animesearch.util.Resource
import shvyn22.animesearch.util.ResourceError
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

    fun updateSelectedImage(newUri: String) {
        _uri.value = newUri
    }

    fun searchImage(bytes: ByteArray) {
        viewModelScope.launch {
            remoteRepository.searchImage(bytes).collect {
                _searchResults.value = it
            }
        }
    }

    fun insertBookmark(item: AnimeModel) {
        viewModelScope.launch {
            localRepository.insertBookmark(fromAnimeModelToBookmark(item))
        }
    }

    fun deleteBookmark(id: Int) {
        viewModelScope.launch {
            localRepository.deleteBookmark(id)
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