package shvyn22.animesearch.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import android.view.View
import androidx.core.content.FileProvider
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.core.Observable
import shvyn22.animesearch.AnimeApp
import shvyn22.animesearch.BuildConfig
import shvyn22.animesearch.R
import shvyn22.animesearch.di.component.SingletonComponent
import java.io.File
import kotlin.math.pow
import kotlin.math.roundToInt

fun RequestBuilder<Drawable>.defaultRequests(): RequestBuilder<Drawable> {
	return this
		.transition(DrawableTransitionOptions.withCrossFade())
		.error(R.drawable.ic_error)
}

fun <T> Observable<T>.toLiveData(): LiveData<T> =
	MutableLiveData<T>().apply {
		this@toLiveData.subscribe { postValue(it) }
	}

fun View.showError(msg: String) {
	Snackbar
		.make(
			this,
			context.getString(R.string.text_error, msg),
			Snackbar.LENGTH_LONG
		)
		.show()
}

fun Context.createTempUri(): Uri =
	FileProvider.getUriForFile(
		this,
		"${BuildConfig.APPLICATION_ID}.provider",
		File.createTempFile(
			"IMG_",
			".jpg",
			getExternalFilesDir(Environment.DIRECTORY_PICTURES)
		)
	)

val Context.singletonComponent: SingletonComponent
	get() = when (this) {
		is AnimeApp -> singletonComponent
		else -> applicationContext.singletonComponent
	}

fun Float.round(decimals: Int): Float {
	val factor = 10.0.pow(decimals).toFloat()
	return (this * factor).roundToInt() / factor
}

fun Float.getPercentage(): Int =
	(this.round(2) * 100).toInt()

fun Float.toStringTime(): String {
	val minutes = this / 60
	val seconds = ((minutes - minutes.toInt()) * 60).toInt()
	return "%02d:%02d".format(minutes.toInt(), seconds)
}