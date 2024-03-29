package br.com.nbagames.designsystem.components.appbar

import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import br.com.nbagames.designsystem.theme.NbaGamesTheme
import br.com.nbagames.designsystem.theme.smallLineHeight

@Composable
fun DefaultTopBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    showBackButton: Boolean = false,
    onBackButtonClick: (() -> Unit)? = null
) {
    TopAppBar(
        modifier = modifier.shadow(smallLineHeight),
        title = {
            if (title != null) {
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    color = textColor,
                    text = title
                )
            }
        },
        backgroundColor = backgroundColor,
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { onBackButtonClick?.invoke() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "backIcon",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun DefaultTopBarPreview() {
    NbaGamesTheme {
        DefaultTopBar(
            title = "Top Bar Align Left",
            showBackButton = true
        )
    }
}
