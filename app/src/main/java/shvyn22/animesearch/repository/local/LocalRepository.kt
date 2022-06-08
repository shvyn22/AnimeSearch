package shvyn22.animesearch.repository.local

import io.reactivex.rxjava3.core.Observable
import shvyn22.animesearch.util.Resource

interface LocalRepository<T> {

    fun getBookmarks(): Observable<Resource<List<T>>>

    fun insertBookmark(item: T)

    fun deleteBookmark(id: Int)

    fun deleteBookmarks()
}