package shvyn22.animesearch.repository.local

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import shvyn22.animesearch.data.local.dao.BookmarkDao
import shvyn22.animesearch.data.local.model.Bookmark
import shvyn22.animesearch.util.Resource
import shvyn22.animesearch.util.ResourceError

class LocalRepositoryImpl(
	private val bookmarkDao: BookmarkDao
) : LocalRepository<Bookmark> {

	override fun getItems(): Observable<Resource<List<Bookmark>>> =
		bookmarkDao.getItems().map { items ->
			if (items.isEmpty()) Resource.Error(ResourceError.NoBookmarks, items)
			else Resource.Success(items)
		}

	override fun insertItem(item: Bookmark) {
		bookmarkDao
			.insert(item)
			.subscribeOn(Schedulers.io())
			.subscribe()
	}

	override fun deleteItem(id: Int) {
		bookmarkDao
			.delete(id)
			.subscribeOn(Schedulers.io())
			.subscribe()
	}

	override fun deleteItems() {
		bookmarkDao
			.deleteAll()
			.subscribeOn(Schedulers.io())
			.subscribe()
	}
}