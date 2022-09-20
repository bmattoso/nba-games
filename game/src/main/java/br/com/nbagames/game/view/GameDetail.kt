package br.com.nbagames.game.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.nbagames.designsystem.theme.largePadding
import br.com.nbagames.designsystem.theme.mediumPadding
import br.com.nbagames.designsystem.theme.smallPadding
import br.com.nbagames.game.presentation.detail.GameDetailViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun GameDetail(
    modifier: Modifier = Modifier,
    gameId: Int,
    onPlayerClick: (playerId: Int) -> Unit,
    onTeamClick: (teamId: Int) -> Unit,
    viewModel: GameDetailViewModel = getViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value

    Column(modifier = modifier.padding(mediumPadding)) {
        if (uiState.game != null) {
            GameCard(
                game = uiState.game.toGamePresentation(),
                modifier = Modifier.defaultMinSize(minHeight = 160.dp)
            )
            Spacer(modifier = Modifier.size(mediumPadding))
            GameQuarterHistory(
                homeTeamName = uiState.game.homeTeam.nickname,
                visitorTeamName = uiState.game.visitantTeam.nickname,
                quarterScoreHistory = uiState.game.quarterScoreHistory,
                currentQuarter = uiState.game.quarter,
                totalHomePoints = uiState.game.homePoints,
                totalVisitorPoints = uiState.game.visitantPoints,
                isGameFinished = uiState.game.isGameFinished //uiState.game.isFinished
            )
        } else {
            viewModel.loadGameDetails(gameId)
        }
    }
}
