package br.com.nbagames.designsystem.components.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.nbagames.designsystem.R
import br.com.nbagames.designsystem.components.TextField
import br.com.nbagames.designsystem.theme.NbaGamesTheme
import br.com.nbagames.designsystem.theme.smallPadding

@Composable
fun NbaProgressIndicator(
    modifier: Modifier = Modifier,
    loadingMessage: Int = R.string.loading,
    animationSize: Int = 100,
    textColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            DefaultLottieAnimation(
                animationResId = R.raw.loading_ball,
                modifier = Modifier.size(animationSize.dp)
            )
            TextField(
                text = stringResource(id = loadingMessage),
                modifier = Modifier.padding(smallPadding),
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NbaGamesTheme {
        NbaProgressIndicator()
    }
}
