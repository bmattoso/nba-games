package br.com.nbagames.game.view

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import br.com.nbagames.designsystem.components.NbaProgressIndicator
import br.com.nbagames.game.presentation.LiveGameViewModel
import br.com.nbagames.game.presentation.LiveGameViewState
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveGameList(
    onLiveGameClick: (gameId: String) -> Unit,
    liveGameViewModel: LiveGameViewModel = getViewModel()
) {
    val liveGameViewState = liveGameViewModel.getLiveGameList().collectAsState(initial = LiveGameViewState.Loading)

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Crossfade(liveGameViewState.value) { currentState ->
            when (currentState) {
                LiveGameViewState.Loading -> NbaProgressIndicator()
                LiveGameViewState.Empty -> Text(text = "Empty")
                is LiveGameViewState.Error -> Text(text = "Error")
                is LiveGameViewState.Loaded -> LiveGameListContent(
                    liveGames = currentState.liveGameList,
                    onLiveGameClick = onLiveGameClick
                )
            }
        }
    }
}
