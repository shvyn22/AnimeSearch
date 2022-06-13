package shvyn22.animesearch.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import shvyn22.animesearch.presentation.ui.theme.dimens

enum class DrawableButtonStyle {
    Wide,
    Rectangular
}

@Composable
fun DrawableButton(
    onClick: () -> Unit,
    text: String,
    textStyle: TextStyle,
    icon: Painter,
    iconSize: Dp,
    padding: Dp,
    modifier: Modifier = Modifier,
    style: DrawableButtonStyle = DrawableButtonStyle.Wide
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .widthIn(min = MaterialTheme.dimens.size.widthButtonMin)
    ) {
        if (style == DrawableButtonStyle.Rectangular) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(iconSize)
                        .padding(bottom = padding)
                )

                Text(
                    text = text,
                    style = textStyle,
                )
            }
        } else {
            Text(
                text = text,
                style = textStyle,
            )

            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = padding)
            )
        }
    }
}