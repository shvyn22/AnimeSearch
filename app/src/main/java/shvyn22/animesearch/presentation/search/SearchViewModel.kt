package shvyn22.animesearch.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import shvyn22.animesearch.data.local.model.AnimeModel
import shvyn22.animesearch.data.local.model.Bookmark
import shvyn22.animesearch.data.util.fromAnimeModelToBookmark
import shvyn22.animesearch.repository.local.LocalRepository
import shvyn22.animesearch.repository.remote.RemoteRepository
import shvyn22.animesearch.util.Resource
import shvyn22.animesearch.util.ResourceError
import shvyn22.animesearch.util.StateEvent
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository<AnimeModel>,
    private val localRepository: LocalRepository<Bookmark>
) : ViewModel() {

    private val _events = PublishSubject.create<StateEvent>()
    val events: Observable<StateEvent> = _events.flatMap { Observable.just(it) }

    private val _uri = MutableLiveData("")
    val uri: LiveData<String> = _uri

    private val _searchResults = MutableLiveData<Resource<List<AnimeModel>>>(Resource.Idle())
    val searchResults: LiveData<Resource<List<AnimeModel>>> = _searchResults

    fun updateSelectedImage(newUri: String) {
        _uri.value = newUri
    }

    fun searchImage(bytes: ByteArray) {
        remoteRepository
            .searchImage(bytes)
            .subscribeOn(Schedulers.io())
            .map { _searchResults.postValue(it) }
            .subscribe()
    }

    fun onAddToBookmarks(item: AnimeModel) {
        localRepository.insertItem(fromAnimeModelToBookmark(item))
    }

    fun onRemoveFromBookmarks(id: Int) {
        localRepository.deleteItem(id)
    }

    fun onNavigateToAnilist(id: Int) {
        _events.onNext(StateEvent.NavigateToAnilist(id))
    }

    fun onErrorOccurred(error: ResourceError) {
        _events.onNext(StateEvent.ShowError(error))
    }
}