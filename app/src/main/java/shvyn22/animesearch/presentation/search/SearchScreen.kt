package shvyn22.animesearch.presentation.search

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import shvyn22.animesearch.R
import shvyn22.animesearch.data.local.model.AnimeModel
import shvyn22.animesearch.presentation.ui.components.DrawableButton
import shvyn22.animesearch.presentation.ui.components.DrawableButtonStyle
import shvyn22.animesearch.presentation.ui.components.ResultItem
import shvyn22.animesearch.presentation.ui.theme.dimens
import shvyn22.animesearch.util.Resource
import shvyn22.animesearch.util.ResourceError
import shvyn22.animesearch.util.StateEvent
import shvyn22.animesearch.util.image.ImagePicker

@Composable
fun SearchScreen(
    imagePicker: ImagePicker,
    onNavigateToBookmarks: () -> Unit,
    onNavigateToAnilist: (Int) -> Unit,
    onErrorOccurred: (ResourceError) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val processResult = { uri: Uri? ->
        if (uri == null)
            viewModel.onErrorOccurred(
                ResourceError.Specified(context.getString(R.string.text_error_loading))
            )
        else
            viewModel.updateSelectedImage(uri.toString())
    }

    val requestPermission = rememberLauncherForActivityResult(RequestPermission()) {
        if (!it) viewModel.onErrorOccurred(
            ResourceError.Specified(context.getString(R.string.text_error_permission))
        )
    }

    val events = viewModel.events.collectAsState(initial = null)
    events.value?.let {
        when (it) {
            is StateEvent.NavigateToAnilist -> onNavigateToAnilist(it.id)
            is StateEvent.ShowError -> onErrorOccurred(it.error)
        }
    }

    val uri = viewModel.uri.collectAsState()
    val resource = viewModel.searchResults.collectAsState()

    SearchContent(
        previewUri = uri.value,
        resource = resource.value,
        onGetImageFromFile = { imagePicker.pickImageFromFile(processResult) },
        onGetImageFromCamera = {
            if (context
                    .checkSelfPermission(
                        Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
            )
                imagePicker.pickImageFromCamera(processResult)
            else
                requestPermission.launch(Manifest.permission.CAMERA)
        },
        onSearch = viewModel::searchImage,
        onInsertBookmark = viewModel::insertBookmark,
        onDeleteBookmark = viewModel::deleteBookmark,
        onNavigateToAnilist = viewModel::onNavigateToAnilist,
        onErrorOccurred = viewModel::onErrorOccurred,
        onNavigateToBookmarks = onNavigateToBookmarks,
        modifier = modifier
    )
}

@Composable
fun SearchContent(
    previewUri: String,
    resource: Resource<List<AnimeModel>>,
    onGetImageFromFile: () -> Unit,
    onGetImageFromCamera: () -> Unit,
    onSearch: (ByteArray) -> Unit,
    onInsertBookmark: (AnimeModel) -> Unit,
    onDeleteBookmark: (Int) -> Unit,
    onNavigateToAnilist: (Int) -> Unit,
    onErrorOccurred: (ResourceError) -> Unit,
    onNavigateToBookmarks: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val (
            btnBookmarks,
            btnFromFile,
            btnFromCamera,
            searchPreview,
            pbLoading,
            listResult,
        ) = createRefs()

        DrawableButton(
            onClick = { onNavigateToBookmarks() },
            text = stringResource(id = R.string.text_navigate_to_bookmarks),
            textStyle = MaterialTheme.typography.body2,
            icon = painterResource(id = R.drawable.ic_not_bookmarked),
            iconSize = MaterialTheme.dimens.size.smallIconSize,
            padding = MaterialTheme.dimens.padding.paddingSmall,
            modifier = Modifier
                .constrainAs(btnBookmarks) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(bottom = MaterialTheme.dimens.padding.paddingLarge)
        )

        DrawableButton(
            onClick = { onGetImageFromFile() },
            text = stringResource(id = R.string.text_upload_from_file),
            textStyle = MaterialTheme.typography.body1,
            icon = painterResource(id = R.drawable.ic_file),
            iconSize = MaterialTheme.dimens.size.largeIconSize,
            padding = MaterialTheme.dimens.padding.paddingSmall,
            style = DrawableButtonStyle.Rectangular,
            modifier = Modifier
                .constrainAs(btnFromFile) {
                    top.linkTo(btnBookmarks.bottom)
                    bottom.linkTo(btnFromCamera.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(btnFromCamera.start)
                }
                .padding(MaterialTheme.dimens.padding.paddingContentSmall),
        )

        DrawableButton(
            onClick = { onGetImageFromCamera() },
            text = stringResource(id = R.string.text_upload_from_camera),
            textStyle = MaterialTheme.typography.body1,
            icon = painterResource(id = R.drawable.ic_camera),
            iconSize = MaterialTheme.dimens.size.largeIconSize,
            padding = MaterialTheme.dimens.padding.paddingSmall,
            style = DrawableButtonStyle.Rectangular,
            modifier = Modifier
                .constrainAs(btnFromCamera) {
                    top.linkTo(btnBookmarks.bottom)
                    start.linkTo(btnFromFile.end)
                    end.linkTo(parent.end)
                }
                .padding(MaterialTheme.dimens.padding.paddingContentSmall),
        )

        if (previewUri.isNotEmpty()) {
            SearchPreview(
                previewUri = previewUri,
                onSearch = onSearch,
                modifier = Modifier
                    .constrainAs(searchPreview) {
                        top.linkTo(btnFromCamera.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(top = MaterialTheme.dimens.padding.paddingLarge)
            )
        }

        when (resource) {
            is Resource.Loading -> CircularProgressIndicator(
                modifier = Modifier
                    .constrainAs(pbLoading) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            is Resource.Error -> onErrorOccurred(resource.error)
            is Resource.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(top = MaterialTheme.dimens.padding.paddingLarge)
                        .constrainAs(listResult) {
                            top.linkTo(searchPreview.bottom)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            height = Dimension.fillToConstraints
                        }
                ) {
                    items(resource.data) { model ->
                        ResultItem(
                            model = model,
                            onInsertBookmark = onInsertBookmark,
                            onDeleteBookmark = onDeleteBookmark,
                            onNavigateToAnilist = onNavigateToAnilist
                        )
                    }
                }
            }
            else -> Unit
        }
    }
}

@Composable
fun SearchPreview(
    previewUri: String,
    onSearch: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Image(
            painter = rememberImagePainter(
                data = previewUri.toUri(),
                builder = {
                    error(R.drawable.ic_error)
                    crossfade(true)
                }
            ),
            contentDescription = stringResource(id = R.string.text_accessibility_preview),
            modifier = Modifier
                .size(
                    width = MaterialTheme.dimens.size.widthImage,
                    height = MaterialTheme.dimens.size.heightImage
                )
        )

        val bytes = LocalContext.current
            .contentResolver
            .openInputStream(previewUri.toUri())
            ?.readBytes()

        DrawableButton(
            onClick = { bytes?.let { onSearch(it) } },
            text = stringResource(id = R.string.text_search),
            textStyle = MaterialTheme.typography.body2,
            icon = painterResource(id = R.drawable.ic_search),
            iconSize = MaterialTheme.dimens.size.smallIconSize,
            padding = MaterialTheme.dimens.padding.paddingSmall
        )
    }
}