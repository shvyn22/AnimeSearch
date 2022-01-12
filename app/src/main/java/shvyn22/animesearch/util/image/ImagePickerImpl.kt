package shvyn22.animesearch.util.image

import android.net.Uri
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.activity.result.contract.ActivityResultContracts.TakePicture
import androidx.lifecycle.LifecycleOwner
import shvyn22.animesearch.util.REGISTRY_KEY

class ImagePickerImpl(
	registry: ActivityResultRegistry,
	lifecycleOwner: LifecycleOwner,
	private val cameraUri: Uri,
) : ImagePicker {
	private var processResult: ((Uri?) -> Unit)? = null

	private val getImageFromFile = registry.register(
		REGISTRY_KEY,
		lifecycleOwner,
		GetContent()
	) { uri: Uri? ->
		processResult?.invoke(uri)
	}


	private val getImageFromCamera = registry.register(
		REGISTRY_KEY,
		lifecycleOwner,
		TakePicture()
	) {
		processResult?.invoke(if (it) cameraUri else null)
	}

	override fun pickImageFromFile(
		callback: (Uri?) -> Unit
	) {
		processResult = callback
		getImageFromFile.launch("image/*")
	}

	override fun pickImageFromCamera(
		callback: (Uri?) -> Unit
	) {
		processResult = callback
		getImageFromCamera.launch(cameraUri)
	}
}