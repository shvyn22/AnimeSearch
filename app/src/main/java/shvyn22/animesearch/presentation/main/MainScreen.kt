package shvyn22.animesearch.presentation.main

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import shvyn22.animesearch.R
import shvyn22.animesearch.presentation.ui.components.AppBar
import shvyn22.animesearch.presentation.ui.components.NavigationConfig
import shvyn22.animesearch.presentation.ui.components.Screen
import shvyn22.animesearch.presentation.ui.theme.dimens
import shvyn22.animesearch.util.ANILIST_URL
import shvyn22.animesearch.util.ActionsState
import shvyn22.animesearch.util.ResourceError
import shvyn22.animesearch.util.image.ImagePicker

@ExperimentalMaterialApi
@Composable
fun MainScreen(
    imagePicker: ImagePicker,
    onToggleTheme: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currRoute = navBackStackEntry?.destination?.route

    val actionsState = ActionsState()

    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    val isDarkTheme = !MaterialTheme.colors.isLight

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                title = when (currRoute) {
                    Screen.Search.route -> stringResource(Screen.Search.title)
                    Screen.Bookmarks.route -> stringResource(Screen.Search.title)
                    else -> null
                },
                isNested = currRoute == Screen.Bookmarks.route,
                onBackPressed = { navController.popBackStack() },
                isDeleteAvailable = currRoute == Screen.Bookmarks.route,
                onDeleteBookmarks = actionsState::executeDeleteBookmarksAction,
                isDarkTheme = isDarkTheme,
                onToggleTheme = onToggleTheme,
            )
        },
    ) {
        NavigationConfig(
            navController = navController,
            imagePicker = imagePicker,
            actionsState = actionsState,
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