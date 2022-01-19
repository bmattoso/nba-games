package br.com.nbagames.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import br.com.nbagames.designsystem.theme.NbaGamesTheme
import br.com.nbagames.home.Home

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NbaGamesTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Home()
                }
            }
        }
    }
}
