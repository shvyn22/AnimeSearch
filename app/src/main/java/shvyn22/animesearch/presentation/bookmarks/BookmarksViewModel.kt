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
		.getItems()
		.subscribeOn(Schedulers.io())
		.toLiveData()

	fun onRemoveFromBookmarks(id: Int) {
		localRepository.deleteItem(id)
	}

	fun onRemoveAllFromBookmarks() {
		localRepository.deleteItems()
	}

	fun onNavigateToAnilist(id: Int) {
		_events.onNext(StateEvent.NavigateToAnilist(id))
	}

	fun onErrorOccurred(error: ResourceError) {
		_events.onNext(StateEvent.ShowError(error))
	}
}