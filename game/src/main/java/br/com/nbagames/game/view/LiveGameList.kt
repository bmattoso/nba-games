package br.com.nbagames.game.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import br.com.nbagames.designsystem.components.NbaProgressIndicator
import br.com.nbagames.game.presentation.LiveGameViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveGameList(
    modifier: Modifier = Modifier,
    onLiveGameClick: (gameId: String) -> Unit,
    liveGameViewModel: LiveGameViewModel = getViewModel()
) {
    val liveGameListUiState = liveGameViewModel.uiState.collectAsState().value

    when {
        liveGameListUiState.showLoading -> NbaProgressIndicator(modifier = Modifier.fillMaxSize())
        liveGameListUiState.showEmptyState -> Text(text = "Empty")
        liveGameListUiState.showError -> Text(text = "Error")
        else -> LiveGameListContent(
            modifier = modifier,
            liveGames = liveGameListUiState.liveGameList,
            onLiveGameClick = onLiveGameClick
        )
    }
}
