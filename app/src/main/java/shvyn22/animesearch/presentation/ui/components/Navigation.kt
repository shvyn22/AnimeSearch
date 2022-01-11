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
import shvyn22.animesearch.util.ResourceError

enum class Routes(
	val route: String,
	@StringRes val title: Int
) {
	Search("search", R.string.nav_search),
	Bookmarks("bookmarks", R.string.nav_bookmarks)
}

@Composable
fun NavigationConfig(
	navController: NavHostController,
	onNavigateToAnilist: (Int) -> Unit,
	onErrorOccurred: (ResourceError) -> Unit,
	onChangeRemoveAll: (() -> Unit) -> Unit,
	modifier: Modifier = Modifier,
) {
	NavHost(
		navController = navController,
		startDestination = Routes.Search.route,
	) {
		composable(Routes.Search.route) {
			SearchScreen(
				onNavigateToBookmarks = {
					navController.navigate(Routes.Bookmarks.route)
				},
				onNavigateToAnilist = onNavigateToAnilist,
				onErrorOccurred = onErrorOccurred,
				modifier = modifier,
			)
		}

		composable(Routes.Bookmarks.route) {
			BookmarksScreen(
				onNavigateToAnilist = onNavigateToAnilist,
				onErrorOccurred = onErrorOccurred,
				onChangeRemoveAll = onChangeRemoveAll,
				modifier = modifier,
			)
		}
	}
}