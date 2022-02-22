package shvyn22.animesearch.repository.remote

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import shvyn22.animesearch.api.ApiInterface
import shvyn22.animesearch.data.local.dao.BookmarkDao
import shvyn22.animesearch.data.local.model.AnimeModel
import shvyn22.animesearch.data.util.fromAnimeDTOToModel
import shvyn22.animesearch.util.Resource
import shvyn22.animesearch.util.ResourceError

class RemoteRepositoryImpl(
	private val api: ApiInterface,
	private val bookmarkDao: BookmarkDao,
) : RemoteRepository<AnimeModel> {

	override fun searchImage(
		bytes: ByteArray
	): Observable<Resource<List<AnimeModel>>> = Observable.create { sub ->
		sub.onNext(Resource.Loading())

		try {
			val image = MultipartBody.Part.createFormData(
				"image", "image", RequestBody.create(
					MediaType.parse("image/*"), bytes
				)
			)

			api
				.searchImage(image)
				.subscribeOn(Schedulers.io())
				.subscribe { response ->
					if (response.error.isEmpty()) {
						bookmarkDao
							.getItems()
							.subscribeOn(Schedulers.io())
							.subscribe { bookmarks ->
								sub.onNext(
									Resource.Success(
										fromAnimeDTOToModel(response.result)
											.distinctBy { it.id }
											.map { model ->
												model.copy(isBookmarked = bookmarks.any {
													it.id == model.id
												})
											}
									)
								)
							}
					} else sub.onNext(Resource.Error(ResourceError.Specified(response.error)))
				}
		} catch (e: Exception) {
			Log.d("DEBUG_TAG", e.message.toString())
			sub.onNext(Resource.Error(ResourceError.Fetching))
		}
	}
}