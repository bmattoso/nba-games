package br.com.nbagames.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.nbagames.designsystem.components.NbaProgressIndicator
import br.com.nbagames.designsystem.theme.NbaGamesTheme

@Composable
fun Splash(onSetupLoaded: () -> Unit) {
    NbaProgressIndicator()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NbaGamesTheme {
        Splash {}
    }
}