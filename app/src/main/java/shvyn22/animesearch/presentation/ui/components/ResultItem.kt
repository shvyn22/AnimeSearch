package shvyn22.animesearch.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import shvyn22.animesearch.data.local.model.AnimeModel
import shvyn22.animesearch.presentation.ui.theme.dimens
import shvyn22.animesearch.presentation.ui.theme.green400
import shvyn22.animesearch.presentation.ui.theme.red
import shvyn22.animesearch.presentation.ui.theme.yellow800

@Composable
fun ResultItem(
    model: AnimeModel,
    onInsertBookmark: (AnimeModel) -> Unit,
    onDeleteBookmark: (Int) -> Unit,
    onNavigateToAnilist: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = MaterialTheme.dimens.shape.elevation,
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
                ivAnilist,
                tvTitle,
                tvSimilarity,
                tvEpisode,
                tvRange,
                ivBookmark,
            ) = createRefs()

            Image(
                painter = rememberImagePainter(
                    data = model.image,
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
                text = stringResource(id = R.string.text_similarity, model.similarity),
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .constrainAs(tvSimilarity) {
                        top.linkTo(parent.top)
                        start.linkTo(ivScreen.end)
                    }
                    .padding(start = MaterialTheme.dimens.padding.paddingSmall)
                    .background(
                        when (model.similarity) {
                            in 0..75 -> red
                            in 75..90 -> yellow800
                            else -> green400
                        }
                    )
                    .padding(MaterialTheme.dimens.padding.paddingSmall)
            )

            Text(
                text = stringResource(id = R.string.text_episode, model.episode),
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .constrainAs(tvEpisode) {
                        top.linkTo(tvSimilarity.top)
                        bottom.linkTo(tvSimilarity.bottom)
                        start.linkTo(tvSimilarity.end)
                    }
                    .padding(start = MaterialTheme.dimens.padding.paddingSmall)
            )

            val (from, to) = model.range
            Text(
                text = stringResource(id = R.string.text_range, from, to),
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .constrainAs(tvRange) {
                        top.linkTo(tvSimilarity.top)
                        bottom.linkTo(tvSimilarity.bottom)
                        start.linkTo(tvEpisode.end)
                    }
                    .padding(start = MaterialTheme.dimens.padding.paddingSmall)
            )

            Icon(
                painter =
                if (model.isAdult)
                    painterResource(id = R.drawable.ic_adult)
                else
                    painterResource(id = R.drawable.ic_not_adult),
                contentDescription = stringResource(id = R.string.text_accessibility_adult),
                modifier = Modifier
                    .constrainAs(ivIsAdult) {
                        top.linkTo(tvSimilarity.top)
                        bottom.linkTo(tvSimilarity.bottom)
                        start.linkTo(tvRange.end)
                    }
                    .padding(start = MaterialTheme.dimens.padding.paddingSmall)
            )

            Text(
                text = stringResource(id = R.string.text_title, model.title),
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .constrainAs(tvTitle) {
                        top.linkTo(tvSimilarity.bottom)
                        start.linkTo(ivScreen.end)
                        end.linkTo(ivIsAdult.end)
                        width = Dimension.fillToConstraints
                    }
                    .padding(
                        top = MaterialTheme.dimens.padding.paddingSmall,
                        start = MaterialTheme.dimens.padding.paddingSmall,
                    )
            )

            Icon(
                painter =
                if (model.isBookmarked)
                    painterResource(id = R.drawable.ic_bookmarked)
                else
                    painterResource(id = R.drawable.ic_not_bookmarked),
                contentDescription = stringResource(id = R.string.text_accessibility_favorite),
                modifier = Modifier
                    .constrainAs(ivBookmark) {
                        top.linkTo(parent.top)
                        bottom.linkTo(ivAnilist.top)
                        start.linkTo(ivAnilist.start)
                        end.linkTo(ivAnilist.end)
                    }
                    .clickable {
                        if (model.isBookmarked) onDeleteBookmark(model.id)
                        else onInsertBookmark(model)
                    }
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_anilist),
                contentDescription = stringResource(id = R.string.text_accessibility_anilist),
                tint = Color.Unspecified,
                modifier = Modifier
                    .constrainAs(ivAnilist) {
                        top.linkTo(ivBookmark.bottom)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
                    .clickable { onNavigateToAnilist(model.id) }
            )
        }
    }
}