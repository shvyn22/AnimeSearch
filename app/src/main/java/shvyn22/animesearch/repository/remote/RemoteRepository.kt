package shvyn22.animesearch.repository.remote

import io.reactivex.rxjava3.core.Observable
import shvyn22.animesearch.util.Resource

interface RemoteRepository<T> {

    fun searchImage(bytes: ByteArray): Observable<Resource<List<T>>>
}