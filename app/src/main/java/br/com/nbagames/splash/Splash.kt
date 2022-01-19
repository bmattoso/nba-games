package br.com.nbagames.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.nbagames.R
import br.com.nbagames.designsystem.color.CustomColors.blackCurrant
import br.com.nbagames.designsystem.components.RollingBall
import br.com.nbagames.designsystem.theme.NbaGamesTheme
import kotlinx.coroutines.delay

private const val SPLASH_DELAY = 2500L

@Composable
fun Splash(
    modifier: Modifier = Modifier,
    onSetupLoaded: () -> Unit
) {
    val currentOnTimeout by rememberUpdatedState(onSetupLoaded)

    LaunchedEffect(true) {
        delay(SPLASH_DELAY)
        currentOnTimeout()
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_nba_games_logo),
            contentDescription = "NBA Games",
            modifier = Modifier.size(200.dp)
        )
        RollingBall()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NbaGamesTheme {
        Splash(modifier = Modifier.background(blackCurrant)) {}
    }
}
