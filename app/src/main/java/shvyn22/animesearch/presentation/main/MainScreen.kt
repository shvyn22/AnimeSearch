package shvyn22.animesearch.presentation.main

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import shvyn22.animesearch.R
import shvyn22.animesearch.presentation.ui.components.AppBar
import shvyn22.animesearch.presentation.ui.components.NavigationConfig
import shvyn22.animesearch.presentation.ui.components.Routes
import shvyn22.animesearch.presentation.ui.theme.dimens
import shvyn22.animesearch.util.ANILIST_URL
import shvyn22.animesearch.util.ResourceError

@Composable
fun MainScreen(
	isNightMode: Boolean,
	onToggleMode: () -> Unit,
	modifier: Modifier = Modifier
) {
	val navController = rememberNavController()
	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currRoute = navBackStackEntry?.destination?.route

	var onRemoveAll by remember { mutableStateOf({}) }

	val context = LocalContext.current

	val coroutineScope = rememberCoroutineScope()
	val scaffoldState = rememberScaffoldState()

	Scaffold(
		scaffoldState = scaffoldState,
		topBar = {
			AppBar(
				title = when (currRoute) {
					Routes.Search.route -> stringResource(Routes.Search.title)
					Routes.Bookmarks.route -> stringResource(Routes.Search.title)
					else -> null
				},
				isNested = currRoute == Routes.Bookmarks.route,
				onBackPressed = { navController.popBackStack() },
				isRemoveAvailable = currRoute == Routes.Bookmarks.route,
				onRemoveAll = onRemoveAll,
				isNightMode = isNightMode,
				onToggleNightMode = onToggleMode,
			)
		},
	) {
		NavigationConfig(
			navController = navController,
			onNavigateToAnilist = {
				val intent = Intent(
					Intent.ACTION_VIEW,
					Uri.parse(ANILIST_URL + it)
				)
				context.startActivity(intent)
			},
			onErrorOccurred = {
				coroutineScope.launch {
					scaffoldState.snackbarHostState.showSnackbar(
						message = when (it) {
							is ResourceError.Fetching ->
								context.getString(R.string.text_error_fetching)
							is ResourceError.Specified -> it.msg
							else -> throw IllegalArgumentException()
						}
					)
				}
			},
			onChangeRemoveAll = { onRemoveAll = it },
			modifier = modifier
				.fillMaxSize()
				.padding(
					top = MaterialTheme.dimens.padding.paddingContentLarge,
					start = MaterialTheme.dimens.padding.paddingContentLarge,
					end = MaterialTheme.dimens.padding.paddingContentLarge,
				)
		)
	}
}