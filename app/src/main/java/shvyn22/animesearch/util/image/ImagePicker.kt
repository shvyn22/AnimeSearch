package shvyn22.animesearch.util.image

import android.net.Uri

interface ImagePicker {
	fun pickImageFromFile(
		callback: (Uri?) -> Unit
	)

	fun pickImageFromCamera(
		callback: (Uri?) -> Unit
	)
}