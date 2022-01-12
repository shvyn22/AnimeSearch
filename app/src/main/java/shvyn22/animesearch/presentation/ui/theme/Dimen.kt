package shvyn22.animesearch.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object Font {
    val fontSizeBody1 = 16.sp
    val fontSizeBody2 = 12.sp
    val letterSpacingBody = 0.5.sp
}

object Shape {
    val smallCornersRadius = 4.dp
    val mediumCornersRadius = 8.dp

    val elevation = 6.dp
}

object Padding {
    val paddingSmall = 5.dp
    val paddingLarge = 10.dp
    val paddingContentSmall = 6.dp
    val paddingContentLarge = 16.dp
}

object Size {
    val widthImage = 100.dp
    val heightImage = 60.dp
    val smallIconSize = 24.dp
    val largeIconSize = 32.dp

    val widthButtonMin = 150.dp
}

data class Dimens(
    val font: Font = Font,
    val shape: Shape = Shape,
    val padding: Padding = Padding,
    val size: Size = Size,
)

val LocalDimens = compositionLocalOf { Dimens() }

val MaterialTheme.dimens: Dimens
    @Composable
    @ReadOnlyComposable
    get() = LocalDimens.current
