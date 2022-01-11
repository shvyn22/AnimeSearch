package shvyn22.animesearch.presentation.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val white = Color(0xFFFFFFFF)
val black700 = Color(0xFF212121)

val green400 = Color(0xFF32CD32)
val yellow800 = Color(0xFFCCCC00)
val red = Color(0xFFFF0000)

val purple50 = Color(0xFFF3E5F5)
val purple300 = Color(0xFFE1BEE7)
val purple600 = Color(0xFF9C27B0)
val purple800 = Color(0xFF7B1FA2)

val LightColors = lightColors(
    primary = purple600,
    primaryVariant = purple800,
    onPrimary = white,
    surface = purple300,
    onSurface = black700,
    background = purple50,
    onBackground = black700,
)

val DarkColors = darkColors(
    primary = purple600,
    primaryVariant = purple800,
    onPrimary = white,
    surface = purple800,
    onSurface = white,
    background = black700,
    onBackground = white,
)
