package shvyn22.animesearch.util

import android.net.Uri
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.activity.result.contract.ActivityResultContracts.TakePicture
import androidx.lifecycle.LifecycleOwner

class ImagePicker(
    registry: ActivityResultRegistry,
    lifecycleOwner: LifecycleOwner,
    private val cameraUri: Uri,
    private val processResult: (Uri?) -> Unit
) {
    private val getImageFromFile = registry.register(
        REGISTRY_KEY,
        lifecycleOwner,
        GetContent()
    ) { uri: Uri? ->
        processResult(uri)
    }

    private val getImageFromCamera = registry.register(
        REGISTRY_KEY,
        lifecycleOwner,
        TakePicture()
    ) {
        processResult(if (it) cameraUri else null)
    }

    fun pickImageFromFile() {
        getImageFromFile.launch("image/*")
    }

    fun pickImageFromCamera() {
        getImageFromCamera.launch(cameraUri)
    }
}