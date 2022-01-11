package shvyn22.animesearch.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import dagger.hilt.android.AndroidEntryPoint
import shvyn22.animesearch.presentation.main.MainScreen
import shvyn22.animesearch.presentation.main.MainViewModel
import shvyn22.animesearch.presentation.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

	private val viewModel: MainViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			val nightMode = viewModel.nightMode.collectAsState()

			AppTheme(
				isNightMode = nightMode.value
			) {
				MainScreen(
					isNightMode = nightMode.value,
					onToggleMode = viewModel::onToggleModeIcon,
				)
			}
		}
	}
}