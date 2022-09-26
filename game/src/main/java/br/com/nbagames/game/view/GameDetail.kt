package br.com.nbagames.game.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.nbagames.designsystem.theme.largePadding
import br.com.nbagames.designsystem.theme.mediumPadding
import br.com.nbagames.designsystem.theme.smallPadding
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
    CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
        Column(
            modifier = modifier
                .padding(mediumPadding)
                .verticalScroll(rememberScrollState())
        ) {
            if (uiState.game != null) {
                GameCard(
                    game = uiState.game.toGamePresentation(),
                    modifier = Modifier
                        .defaultMinSize(minHeight = 160.dp)
                        .padding(top = smallPadding)
                )
                Spacer(modifier = Modifier.size(mediumPadding))
                GameQuarterHistory(
                    homeTeamName = uiState.game.homeTeam.nickname,
                    visitorTeamName = uiState.game.visitantTeam.nickname,
                    quarterScoreHistory = uiState.game.quarterScoreHistory,
                    currentQuarter = uiState.game.quarter,
                    totalHomePoints = uiState.game.homePoints,
                    totalVisitorPoints = uiState.game.visitantPoints,
                    isGameFinished = uiState.game.isGameFinished
                )
                Spacer(modifier = Modifier.size(mediumPadding))
                GameBoxScore(
                    modifier = Modifier.padding(mediumPadding),
                    homeTeamName = uiState.game.homeTeam.name,
                    visitorTeamName = uiState.game.visitantTeam.name,
                    onPlayerClick = onPlayerClick
                )
            } else {
                viewModel.loadGameDetails(gameId)
            }
        }
    }
}
