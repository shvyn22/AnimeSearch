package shvyn22.animesearch.presentation.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import shvyn22.animesearch.R

@Composable
fun AppBar(
    title: String?,
    isNested: Boolean,
    onBackPressed: () -> Unit,
    isDeleteAvailable: Boolean,
    onDeleteBookmarks: () -> Unit,
    isDarkTheme: Boolean,
    onToggleTheme: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(text = title?.capitalize(Locale.current) ?: stringResource(id = R.string.app_name))
        },
        actions = {
            if (isDeleteAvailable) {
                IconButton(
                    onClick = { onDeleteBookmarks() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = stringResource(id = R.string.text_accessibility_remove)
                    )
                }
            }

            IconButton(
                onClick = { onToggleTheme(!isDarkTheme) }
            ) {
                Icon(
                    imageVector = if (isDarkTheme) Icons.Filled.LightMode
                    else Icons.Filled.DarkMode,
                    contentDescription = stringResource(id = R.string.text_accessibility_mode)
                )
            }
        },
        navigationIcon = if (!isNested) null else {
            {
                IconButton(
                    onClick = { onBackPressed() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        },
        modifier = modifier
    )
}