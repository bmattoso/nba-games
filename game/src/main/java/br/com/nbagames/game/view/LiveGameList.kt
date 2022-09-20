package br.com.nbagames.game.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Switch
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import br.com.nbagames.designsystem.components.CommunicationSection
import br.com.nbagames.designsystem.components.loading.NbaProgressIndicator
import br.com.nbagames.designsystem.theme.largePadding
import br.com.nbagames.designsystem.theme.smallPadding
import br.com.nbagames.game.R
import br.com.nbagames.game.presentation.LiveGameListError
import br.com.nbagames.game.presentation.LiveGameViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun LiveGameList(
    modifier: Modifier = Modifier,
    onLiveGameClick: (gameId: Int) -> Unit,
    onClickOpenCalendar: () -> Unit,
    liveGameViewModel: LiveGameViewModel = getViewModel()
) {
    val uiState = liveGameViewModel.uiState.collectAsState().value

    with(uiState) {
        when {
            showLoading && liveGameList.isEmpty() -> NbaProgressIndicator(modifier = modifier)
            showEmptyState -> LiveGameListEmptyState(
                modifier = modifier.padding(largePadding),
                onClickOpenCalendar = onClickOpenCalendar
            )
            showError && liveGameListError != null -> LiveGameListErrorState(
                modifier = modifier.padding(largePadding),
                liveGameListError = liveGameListError,
                onClickTryAgain = { liveGameViewModel.loadLiveGameList() }
            )
            else ->
                Column(modifier = modifier) {
                    CountdownUpdate(
                        modifier = Modifier.fillMaxWidth(),
                        countdownTimer = countdownTimer,
                        isCountdownAvailable = isCountdownAvailable,
                        onToggleCountdown = liveGameViewModel::toggleCountdownTimer
                    )
                    LiveGameListContent(
                        liveGames = liveGameList,
                        onLiveGameClick = { gameId ->
                            liveGameViewModel.onGameClick()
                            onLiveGameClick(gameId)
                        }
                    )
                }
        }
    }
}

@Composable
fun CountdownUpdate(
    modifier: Modifier = Modifier,
    countdownTimer: String,
    isCountdownAvailable: Boolean,
    onToggleCountdown: () -> Unit
) {
    val countdownText = if (isCountdownAvailable) {
        stringResource(id = R.string.countdown_timer_hint, countdownTimer)
    } else {
        stringResource(id = R.string.countdown_timer_paused)
    }

    Row(
        modifier = modifier.background(color = MaterialTheme.colorScheme.primaryContainer),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = countdownText,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = stringResource(id = R.string.sync),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.size(smallPadding))
        Switch(
            checked = isCountdownAvailable,
            onCheckedChange = { onToggleCountdown() }
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
        animationRes = liveGameListError.animationRes
    ) {
        Button(onClick = onClickTryAgain) {
            Text(text = stringResource(id = liveGameListError.actionMessage))
        }
    }
}
