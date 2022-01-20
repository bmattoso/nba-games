package br.com.nbagames.designsystem.components

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.nbagames.designsystem.R
import br.com.nbagames.designsystem.components.loading.DefaultLottieAnimation
import br.com.nbagames.designsystem.theme.NbaGamesTheme

@Composable
fun EmptyState(
    modifier: Modifier = Modifier,
    @StringRes message: Int,
    @RawRes iconRes: Int = R.raw.nothing_found,
    actionContent: @Composable () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = CenterHorizontally
    ) {
        DefaultLottieAnimation(
            modifier = Modifier.size(240.dp),
            animationResId = iconRes,
            iterations = 2
        )
        TextField(text = stringResource(id = message), textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.size(16.dp))
        actionContent()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NbaGamesTheme {
        Surface {
            EmptyState(
                modifier = Modifier.fillMaxSize(),
                message = R.string.mtrl_picker_toggle_to_year_selection
            ) {
                Button(onClick = {}) {
                    Text(text = "Try load again")
                }
            }
        }
    }
}
