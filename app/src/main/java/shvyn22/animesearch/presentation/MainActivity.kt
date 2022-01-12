package shvyn22.animesearch.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import dagger.hilt.android.AndroidEntryPoint
import shvyn22.animesearch.presentation.main.MainScreen
import shvyn22.animesearch.presentation.main.MainViewModel
import shvyn22.animesearch.presentation.ui.theme.AppTheme
import shvyn22.animesearch.util.createTempUri
import shvyn22.animesearch.util.image.ImagePickerImpl
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

	@Inject
	lateinit var registry: ActivityResultRegistry

	private val viewModel: MainViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val imagePicker = ImagePickerImpl(
			registry = registry,
			lifecycleOwner = this,
			cameraUri = createTempUri()
		)

		setContent {
			val nightMode = viewModel.nightMode.collectAsState()

			AppTheme(
				isNightMode = nightMode.value
			) {
				MainScreen(
					isNightMode = nightMode.value,
					onToggleMode = viewModel::onToggleModeIcon,
					imagePicker = imagePicker
				)
			}
		}
	}
}