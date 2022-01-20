package br.com.nbagames.game.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.nbagames.designsystem.components.CommunicationSection
import br.com.nbagames.designsystem.components.loading.NbaProgressIndicator
import br.com.nbagames.game.R
import br.com.nbagames.game.presentation.LiveGameListError
import br.com.nbagames.game.presentation.LiveGameViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveGameList(
    modifier: Modifier = Modifier,
    onLiveGameClick: (gameId: String) -> Unit,
    onClickOpenCalendar: () -> Unit,
    liveGameViewModel: LiveGameViewModel = getViewModel()
) {
    val liveGameListUiState = liveGameViewModel.uiState.collectAsState().value

    when {
        liveGameListUiState.showLoading -> NbaProgressIndicator(modifier = modifier)
        liveGameListUiState.showEmptyState -> LiveGameListEmptyState(
            modifier = modifier.padding(16.dp),
            onClickOpenCalendar = onClickOpenCalendar
        )
        liveGameListUiState.showError && liveGameListUiState.liveGameListError != null -> LiveGameListErrorState(
            modifier = modifier.padding(16.dp),
            liveGameListError = liveGameListUiState.liveGameListError,
            onClickTryAgain = { liveGameViewModel.loadLiveGameList() }
        )
        else -> LiveGameListContent(
            modifier = modifier,
            liveGames = liveGameListUiState.liveGameList,
            onLiveGameClick = onLiveGameClick
        )
    }
}

@Composable
private fun LiveGameListEmptyState(
    modifier: Modifier = Modifier,
    onClickOpenCalendar: () -> Unit
) {
    CommunicationSection(
        modifier = modifier,
        message = R.string.empty_live_games
    ) {
        Button(onClick = onClickOpenCalendar) {
            Text(text = stringResource(id = R.string.open_calendar))
        }
    }
}

@Composable
private fun LiveGameListErrorState(
    modifier: Modifier = Modifier,
    liveGameListError: LiveGameListError,
    onClickTryAgain: () -> Unit
) {
    CommunicationSection(
        modifier = modifier,
        message = liveGameListError.message,
        animationRes = liveGameListError.animationRes,
    ) {
        Button(onClick = onClickTryAgain) {
            Text(text = stringResource(id = liveGameListError.actionMessage))
        }
    }
}
