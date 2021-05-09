package br.com.nbagames.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.nbagames.designsystem.R
import br.com.nbagames.designsystem.theme.NbaGamesTheme
import br.com.nbagames.designsystem.theme.smallPadding
import com.airbnb.lottie.compose.rememberLottieAnimationState

@Composable
fun NbaProgressIndicator() {
    val animationState = rememberLottieAnimationState(
        autoPlay = true, repeatCount = Integer.MAX_VALUE
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.blackOpacity40)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DefaultLottieAnimation(
                animationResId = R.raw.loading_ball,
                modifier = Modifier.size(100.dp),
                animationState = animationState
            )
            TextField(
                text = stringResource(id = R.string.loading),
                modifier = Modifier.padding(smallPadding),
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
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