package shvyn22.animesearch.repository.local

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import shvyn22.animesearch.data.local.dao.BookmarkDao
import shvyn22.animesearch.data.local.model.Bookmark
import shvyn22.animesearch.util.Resource
import shvyn22.animesearch.util.ResourceError

class LocalRepositoryImpl(
    private val dao: BookmarkDao
) : LocalRepository<Bookmark> {

    override fun getBookmarks(): Observable<Resource<List<Bookmark>>> =
        dao.getBookmarks().map { items ->
            if (items.isEmpty())
                Resource.Error(ResourceError.NoBookmarks, items)
            else
                Resource.Success(items)
        }

    override fun insertBookmark(item: Bookmark) {
        dao
            .insertBookmark(item)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun deleteBookmark(id: Int) {
        dao
            .deleteBookmark(id)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun deleteBookmarks() {
        dao
            .deleteBookmarks()
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}