package shvyn22.animesearch.repository.local

import io.reactivex.rxjava3.core.Observable
import shvyn22.animesearch.util.Resource

interface LocalRepository<T> {

    fun getItems(): Observable<Resource<List<T>>>

    fun insertItem(item: T)

    fun deleteItem(id: Int)

    fun deleteItems()
}