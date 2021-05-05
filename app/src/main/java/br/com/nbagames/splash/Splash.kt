package br.com.nbagames.splash

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.nbagames.designsystem.theme.NbaGamesTheme

@Composable
fun Splash(onSetupLoaded: () -> Unit) {
    Text(text = "Hello!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NbaGamesTheme {
        Splash {}
    }
}