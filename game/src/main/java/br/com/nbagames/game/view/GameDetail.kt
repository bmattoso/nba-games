package br.com.nbagames.game.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.nbagames.designsystem.components.CommunicationSection
import br.com.nbagames.designsystem.components.loading.NbaProgressIndicator
import br.com.nbagames.designsystem.theme.largePadding
import br.com.nbagames.designsystem.theme.mediumPadding
import br.com.nbagames.designsystem.theme.smallPadding
import br.com.nbagames.game.presentation.CommonError
import br.com.nbagames.game.presentation.detail.GameDetailViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameDetail(
    modifier: Modifier = Modifier,
    gameId: Int,
    onPlayerClick: (playerId: Int) -> Unit,
    onTeamClick: (teamId: Int) -> Unit,
    viewModel: GameDetailViewModel = getViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.loadGameDetails(gameId)
    }

    Column(
        modifier = modifier
            .padding(mediumPadding)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {
        if (uiState.showLoading) {
            NbaProgressIndicator(modifier = Modifier.fillMaxSize())
        }
        if (uiState.error != null) {
            ErrorState(commonError = uiState.error) {
                viewModel.loadGameDetails(gameId)
            }
        }
        if (uiState.game != null) {
            GameCard(
                game = uiState.game.toGamePresentation(),
                modifier = Modifier
                    .defaultMinSize(minHeight = 160.dp)
                    .padding(top = smallPadding)
            )
            CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
                if (uiState.game.quarterScoreHistory != null) {
                    Spacer(modifier = Modifier.size(mediumPadding))
                    GameQuarterHistory(
                        modifier = Modifier.fillMaxWidth(),
                        homeTeamName = uiState.game.homeTeam.nickname,
                        visitorTeamName = uiState.game.visitantTeam.nickname,
                        quarterScoreHistory = uiState.game.quarterScoreHistory,
                        currentQuarter = uiState.game.quarter,
                        totalHomePoints = uiState.game.homePoints,
                        totalVisitorPoints = uiState.game.visitantPoints,
                        isGameFinished = uiState.game.isGameFinished
                    )
                    Spacer(modifier = Modifier.size(largePadding))
                }
                if (uiState.game.gameStatistics != null) {
                    GameBoxScore(
                        homeTeamName = uiState.game.homeTeam.name,
                        visitorTeamName = uiState.game.visitantTeam.name,
                        gameStatistics = uiState.game.gameStatistics,
                        onPlayerClick = onPlayerClick
                    )
                }
            }
            if (uiState.game.officials.isNotEmpty()) {
                Spacer(modifier = Modifier.size(mediumPadding))
                GameOfficials(
                    modifier = Modifier.padding(bottom = smallPadding),
                    officials = uiState.game.officials
                )
            }
        }
    }
}

@Composable
private fun ErrorState(
    modifier: Modifier = Modifier,
    commonError: CommonError,
    onClickTryAgain: () -> Unit
) {
    CommunicationSection(
        modifier = modifier,
        message = commonError.message,
        animationRes = commonError.animationRes
    ) {
        Button(onClick = onClickTryAgain) {
            Text(text = stringResource(id = commonError.actionMessage))
        }
    }
}
