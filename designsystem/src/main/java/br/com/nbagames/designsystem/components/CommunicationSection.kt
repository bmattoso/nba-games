package br.com.nbagames.designsystem.components

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
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
import br.com.nbagames.designsystem.theme.largePadding

@Composable
fun CommunicationSection(
    modifier: Modifier = Modifier,
    @StringRes message: Int,
    @RawRes animationRes: Int = R.raw.nothing_found,
    animationSize: Int = 240,
    actionContent: @Composable () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = CenterHorizontally
    ) {
        DefaultLottieAnimation(
            modifier = Modifier.size(animationSize.dp),
            animationResId = animationRes,
            iterations = 2
        )
        Text(
            text = stringResource(id = message),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.size(largePadding))
        actionContent()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NbaGamesTheme {
        Surface {
            CommunicationSection(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(largePadding),
                message = R.string.unknown_error_message
            ) {
                Button(onClick = {}) {
                    Text(text = "Try load again")
                }
            }
        }
    }
}
