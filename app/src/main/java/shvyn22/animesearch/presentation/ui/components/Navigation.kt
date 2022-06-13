package shvyn22.animesearch.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import shvyn22.animesearch.R
import shvyn22.animesearch.presentation.bookmarks.BookmarksScreen
import shvyn22.animesearch.presentation.search.SearchScreen
import shvyn22.animesearch.util.ActionsState
import shvyn22.animesearch.util.ResourceError
import shvyn22.animesearch.util.image.ImagePicker

enum class Screen(
    val route: String,
    @StringRes val title: Int
) {
    Search("search", R.string.nav_search),
    Bookmarks("bookmarks", R.string.nav_bookmarks)
}

@Composable
fun NavigationConfig(
    navController: NavHostController,
    imagePicker: ImagePicker,
    actionsState: ActionsState,
    onNavigateToAnilist: (Int) -> Unit,
    onErrorOccurred: (ResourceError) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Search.route,
    ) {
        composable(Screen.Search.route) {
            SearchScreen(
                imagePicker = imagePicker,
                onNavigateToBookmarks = {
                    navController.navigate(Screen.Bookmarks.route)
                },
                onNavigateToAnilist = onNavigateToAnilist,
                onErrorOccurred = onErrorOccurred,
                modifier = modifier,
            )
        }

        composable(Screen.Bookmarks.route) {
            BookmarksScreen(
                actionsState = actionsState,
                onNavigateToAnilist = onNavigateToAnilist,
                onErrorOccurred = onErrorOccurred,
                modifier = modifier,
            )
        }
    }
}