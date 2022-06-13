package shvyn22.animesearch.presentation.bookmarks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import shvyn22.animesearch.R
import shvyn22.animesearch.data.local.model.Bookmark
import shvyn22.animesearch.presentation.ui.components.BookmarkItem
import shvyn22.animesearch.presentation.ui.theme.dimens
import shvyn22.animesearch.util.ActionsState
import shvyn22.animesearch.util.Resource
import shvyn22.animesearch.util.ResourceError
import shvyn22.animesearch.util.StateEvent

@Composable
fun BookmarksScreen(
    actionsState: ActionsState,
    onNavigateToAnilist: (Int) -> Unit,
    onErrorOccurred: (ResourceError) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookmarksViewModel = hiltViewModel(),
) {
    val events = viewModel.events.collectAsState(initial = null)
    events.value?.let {
        when (it) {
            is StateEvent.NavigateToAnilist -> onNavigateToAnilist(it.id)
            is StateEvent.ShowError -> onErrorOccurred(it.error)
        }
    }

    val resource = viewModel.bookmarks.collectAsState()

    actionsState.setDeleteBookmarksAction(viewModel::deleteBookmarks)

    BookmarksContent(
        resource = resource.value,
        onDeleteBookmark = viewModel::deleteBookmark,
        onNavigateToAnilist = viewModel::onNavigateToAnilist,
        onErrorOccurred = viewModel::onErrorOccurred,
        modifier = modifier,
    )
}

@Composable
fun BookmarksContent(
    resource: Resource<List<Bookmark>>,
    onDeleteBookmark: (Int) -> Unit,
    onNavigateToAnilist: (Int) -> Unit,
    onErrorOccurred: (ResourceError) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        var bookmarks = emptyList<Bookmark>()
        var hintText by remember { mutableStateOf("") }

        when (resource) {
            is Resource.Loading -> CircularProgressIndicator()
            is Resource.Error -> {
                if (resource.error is ResourceError.NoBookmarks) {
                    resource.data?.let { bookmarks = it }
                    hintText = stringResource(id = R.string.text_error_no_bookmarks)
                } else onErrorOccurred(resource.error)
            }
            is Resource.Success -> {
                bookmarks = resource.data
                hintText = stringResource(id = R.string.text_hint)
            }
            else -> Unit
        }

        Text(
            text = hintText,
            modifier = Modifier
                .padding(bottom = MaterialTheme.dimens.padding.paddingSmall)
        )

        LazyColumn(
            modifier = Modifier
                .padding(top = MaterialTheme.dimens.padding.paddingLarge)
        ) {
            items(bookmarks) { bookmark ->
                // TODO: implement swipe
                BookmarkItem(
                    bookmark = bookmark,
                    onNavigateToAnilist = onNavigateToAnilist,
                )
            }
        }
    }
}