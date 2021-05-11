package br.com.nbagames.game.view

import androidx.compose.animation.Crossfade
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import br.com.nbagames.designsystem.components.NbaProgressIndicator
import br.com.nbagames.game.presentation.LiveGameListState
import br.com.nbagames.game.presentation.LiveGameViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun LiveGameList(
    onLiveGameClick: (gameId: String) -> Unit,
    liveGameViewModel: LiveGameViewModel = getViewModel()
) {
    val liveGameListState = liveGameViewModel.getLiveGameList()
        .collectAsState(initial = LiveGameListState.Loading)

    Crossfade(liveGameListState.value) { currentState ->
        when (currentState) {
            LiveGameListState.Loading -> NbaProgressIndicator()
            LiveGameListState.Empty -> Text(text = "Empty")
            is LiveGameListState.Error -> Text(text = "Error")
            is LiveGameListState.Loaded -> LiveGameListContent(
                liveGames = currentState.liveGameList,
                onLiveGameClick = onLiveGameClick
            )
        }
    }
}
