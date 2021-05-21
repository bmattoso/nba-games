package br.com.nbagames.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import br.com.nbagames.R
import br.com.nbagames.designsystem.components.RollingBall
import br.com.nbagames.designsystem.components.TextField
import br.com.nbagames.designsystem.theme.NbaGamesTheme
import kotlinx.coroutines.delay

private const val SPLASH_DELAY = 2500L

@Composable
fun Splash(onSetupLoaded: () -> Unit) {
    val currentOnTimeout by rememberUpdatedState(onSetupLoaded)

    LaunchedEffect(true) {
        delay(SPLASH_DELAY)
        currentOnTimeout()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.blackCurrant)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        RollingBall()
        TextField(
            text = stringResource(id = R.string.app_name),
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NbaGamesTheme {
        Splash {}
    }
}
