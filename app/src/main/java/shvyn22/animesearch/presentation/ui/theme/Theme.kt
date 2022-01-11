package shvyn22.animesearch.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AppTheme(
    isNightMode: Boolean,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalDimens provides Dimens()) {
        val colors = if (isNightMode) DarkColors else LightColors

        val systemUiController = rememberSystemUiController()
        systemUiController.setStatusBarColor(colors.primaryVariant)

        MaterialTheme(
            colors = colors,
            typography = AppTypography,
            shapes = AppShapes,
            content = content
        )
    }
}