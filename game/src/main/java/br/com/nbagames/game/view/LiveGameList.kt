package br.com.nbagames.game.view

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import br.com.nbagames.designsystem.components.NbaProgressIndicator
import br.com.nbagames.game.presentation.LiveGameViewState
import br.com.nbagames.game.presentation.LiveGameViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun LiveGameList(
    onLiveGameClick: (gameId: String) -> Unit,
    liveGameViewModel: LiveGameViewModel = getViewModel()
) {
    val liveGameViewState = liveGameViewModel.getLiveGameList()
        .collectAsState(initial = LiveGameViewState.Loading)

    Scaffold(modifier = Modifier.fillMaxSize()) {
        LiveGameContent(
            onLiveGameClick = onLiveGameClick,
            liveGameViewState = liveGameViewState.value
        )
    }
}

@Composable
private fun LiveGameContent(
    onLiveGameClick: (gameId: String) -> Unit,
    liveGameViewState: LiveGameViewState
) {
    Crossfade(liveGameViewState) { currentState ->
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
