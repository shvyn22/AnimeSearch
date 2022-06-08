package shvyn22.animesearch.presentation.bookmarks

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import shvyn22.animesearch.data.local.model.Bookmark
import shvyn22.animesearch.repository.local.LocalRepository
import shvyn22.animesearch.util.ResourceError
import shvyn22.animesearch.util.StateEvent
import shvyn22.animesearch.util.toLiveData
import javax.inject.Inject

class BookmarksViewModel @Inject constructor(
    private val localRepository: LocalRepository<Bookmark>,
) : ViewModel() {

    private val _events = PublishSubject.create<StateEvent>()
    val events: Observable<StateEvent> = _events.flatMap { Observable.just(it) }

    fun getBookmarks() = localRepository
        .getBookmarks()
        .subscribeOn(Schedulers.io())
        .toLiveData()

    fun deleteBookmark(id: Int) {
        localRepository.deleteBookmark(id)
    }

    fun deleteBookmarks() {
        localRepository.deleteBookmarks()
    }

    fun onNavigateToAnilist(id: Int) {
        _events.onNext(StateEvent.NavigateToAnilist(id))
    }

    fun onErrorOccurred(error: ResourceError) {
        _events.onNext(StateEvent.ShowError(error))
    }
}