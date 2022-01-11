package shvyn22.animesearch.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import shvyn22.animesearch.R
import shvyn22.animesearch.data.local.model.Bookmark
import shvyn22.animesearch.presentation.ui.theme.dimens

@Composable
fun BookmarkItem(
	bookmark: Bookmark,
	onNavigateToAnilist: (Int) -> Unit,
	modifier: Modifier = Modifier,
) {
	Card(
		modifier = modifier
			.fillMaxWidth()
			.padding(
				vertical = MaterialTheme.dimens.padding.paddingSmall,
			)
	) {
		ConstraintLayout(
			modifier = Modifier.padding(
				MaterialTheme.dimens.padding.paddingSmall
			)
		) {
			val (
				ivScreen,
				ivIsAdult,
				tvTitle,
				ivAnilist,
			) = createRefs()

			Image(
				painter = rememberImagePainter(
					data = bookmark.image,
					builder = {
						error(R.drawable.ic_error)
						crossfade(true)
					}
				),
				contentDescription = stringResource(id = R.string.text_accessibility_preview),
				modifier = Modifier
					.size(
						width = MaterialTheme.dimens.size.widthImage,
						height = MaterialTheme.dimens.size.heightImage,
					)
					.constrainAs(ivScreen) {
						top.linkTo(parent.top)
						bottom.linkTo(parent.bottom)
						start.linkTo(parent.start)
					}
			)

			Text(
				text = bookmark.title,
				style = MaterialTheme.typography.body2,
				modifier = Modifier
					.constrainAs(tvTitle) {
						top.linkTo(ivIsAdult.top)
						bottom.linkTo(ivAnilist.bottom)
						start.linkTo(ivScreen.end)
						end.linkTo(ivAnilist.start)
						width = Dimension.fillToConstraints
					}
					.padding(
						start = MaterialTheme.dimens.padding.paddingSmall
					)
			)

			Icon(
				painter =
				if (bookmark.isAdult)
					painterResource(id = R.drawable.ic_adult)
				else
					painterResource(id = R.drawable.ic_not_adult),
				contentDescription = stringResource(id = R.string.text_accessibility_adult),
				modifier = Modifier
					.constrainAs(ivIsAdult) {
						top.linkTo(parent.top)
						bottom.linkTo(ivAnilist.top)
						start.linkTo(ivAnilist.start)
						end.linkTo(ivAnilist.end)
					}
			)

			Icon(
				painter = painterResource(id = R.drawable.ic_anilist),
				contentDescription = stringResource(id = R.string.text_accessibility_anilist),
				tint = Color.Unspecified,
				modifier = Modifier
					.constrainAs(ivAnilist) {
						top.linkTo(ivIsAdult.bottom)
						bottom.linkTo(parent.bottom)
						end.linkTo(parent.end)
					}
					.clickable { onNavigateToAnilist(bookmark.id) }
			)
		}
	}
}