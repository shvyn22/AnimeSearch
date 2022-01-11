package shvyn22.animesearch.util

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import shvyn22.animesearch.BuildConfig
import java.io.File
import kotlin.math.pow
import kotlin.math.roundToInt

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